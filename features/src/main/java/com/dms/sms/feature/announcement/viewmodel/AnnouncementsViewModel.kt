package com.dms.sms.feature.announcement.viewmodel

import androidx.lifecycle.*
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.usecase.GetAnnouncementsUseCase
import com.dms.domain.announcement.usecase.SearchAnnouncementsUseCase
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.announcement.model.SearchAnnouncementsModel
import com.dms.sms.feature.announcement.model.SimpleAnnouncementModel
import com.dms.sms.feature.announcement.model.toDomainData
import com.dms.sms.feature.announcement.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class AnnouncementsViewModel(
    private val getAnnouncementsUseCase: GetAnnouncementsUseCase,
    private val searchAnnouncementsUseCase: SearchAnnouncementsUseCase
) :
    BaseViewModel(), LifecycleObserver {


    private val _announcements = MutableLiveData<List<SimpleAnnouncementModel>>()
    val announcements: LiveData<List<SimpleAnnouncementModel>> get() = _announcements

    private val _announcementsPages = MutableLiveData<List<List<String>>>()
    val announcementsPages: LiveData<List<List<String>>> get() = _announcementsPages

    private val _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int> get() = _currentPage

    private val _currentPageBunch = MutableLiveData(0)
    val currentPageBunch: LiveData<Int> get() = _currentPageBunch

    val searchQuery = MutableLiveData<String>()

    val isSearched = MutableLiveData(false)

    val announcementEvent = SingleLiveEvent<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        _currentPage.value = 0
        getAnnouncements(currentPage.value!!)
    }


    fun onBackPressed(){
        isSearched.value = false
        _currentPage.value = 0
        _currentPageBunch.value = 0
        getAnnouncements(currentPage.value!!)
    }
    fun onClickAnnouncement(announcementUUID: String) {
        announcementEvent.value = announcementUUID
    }

    fun onSearchPressed(searchQuery: String) {
        if(searchQuery.isNotEmpty() && searchQuery.isNotBlank()){
            searchAnnouncements(searchQuery, 0)
            _currentPageBunch.value = 0
        }
        else
            createToastEvent.value = "글을 입력해주세요"
    }

    private fun searchAnnouncements(searchQuery: String, announcementsPage: Int) {
        searchAnnouncementsUseCase.execute(
            SearchAnnouncementsModel(
                searchQuery,
                announcementsPage
            ).toDomainData(), object : DisposableSingleObserver<Result<Announcements>>() {
                override fun onSuccess(result: Result<Announcements>) {
                    when (result) {
                        is Result.Success -> {
                            _announcements.value =
                                result.value.simpleAnnouncements.map { it.toModel() }
                            isSearched.value = true
                            _announcementsPages.value = makePages(result.value.size)
                            _currentPage.value = announcementsPage

                        }
                        is Result.Failure -> {
                            onFailedToLoadAnnouncements(result)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = "오류 발생"

                }
            }, AndroidSchedulers.mainThread()
        )
    }


    private fun getAnnouncements(announcementsPage: Int) {
        getAnnouncementsUseCase.execute(
            announcementsPage, object : DisposableSingleObserver<Result<Announcements>>() {
                override fun onSuccess(result: Result<Announcements>) {
                    when (result) {
                        is Result.Success -> {
                            _announcements.value =
                                result.value.simpleAnnouncements.map { it.toModel() }

                            _announcementsPages.value = makePages(result.value.size)
                        }
                        is Result.Failure -> {
                            onFailedToLoadAnnouncements(result)
                        }

                    }
                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = "오류 발생"
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun makePages(announcementSize: Int): ArrayList<ArrayList<String>> {
        val pageSize = if (announcementSize % 8 != 0)
            announcementSize / 8 + 1
        else
            announcementSize / 8

        val pageList = ArrayList<ArrayList<String>>()
        pageList.add(ArrayList())
        var currentPage = 0

        for (i in 1..pageSize) {
            pageList[currentPage].add(i.toString())
            if (i % 5 == 0) {
                pageList.add(ArrayList())
                currentPage += 1
            }
        }
        return pageList

    }

    fun onPageClick(page: String) {
        _currentPage.value = page.toInt() - 1
        if (isSearched.value!!) {
            searchAnnouncements(searchQuery.value!!, currentPage.value!!)
        } else
            getAnnouncements(currentPage.value!!)

    }

    fun onNextPageClick() {
        if (hasNextPage()) {
            _currentPageBunch.value = _currentPageBunch.value!! + 1
            _currentPage.value =
                _announcementsPages.value!![currentPageBunch.value!!][0].toInt() - 1
            if (isSearched.value!!)
                searchAnnouncements(searchQuery.value!!, currentPage.value!!)
            else
                getAnnouncements(currentPage.value!!)
        }
    }

    fun onPreviousPageClick() {
        if (hasPreviousPage()) {
            _currentPageBunch.value = _currentPageBunch.value!! - 1
            _currentPage.value =
                _announcementsPages.value!![currentPageBunch.value!!][0].toInt() - 1
            if (isSearched.value!!)
                searchAnnouncements(searchQuery.value!!, currentPage.value!!)
            else
                getAnnouncements(currentPage.value!!)
        }
    }

    private fun hasNextPage(): Boolean {
        if (currentPageBunch.value!! + 1 >= announcementsPages.value!!.size)
            return false
        return true
    }

    private fun hasPreviousPage(): Boolean {
        if (currentPageBunch.value!! > 0)
            return true
        return false
    }


    fun onFailedToLoadAnnouncements(result: Result.Failure<Announcements>) {
        when (result.reason) {
            Error.Network -> createToastEvent.value = "네트워크 오류 발생"
            Error.BadRequest -> createToastEvent.value = "오류 발생"
            Error.UnAuthorized -> createToastEvent.value = "인증되지 않은 사용자입니다"
            Error.Forbidden -> createToastEvent.value = "오류 발생"
            Error.NotFound -> createToastEvent.value = "글이 없습니다"
            Error.Timeout -> createToastEvent.value = "요청 시간이 너무 오래 걸립니다"
            Error.Conflict -> createToastEvent.value = "오류 발생"
            Error.InternalServer -> createToastEvent.value = "서버 오류 발생"
            Error.Unknown -> createToastEvent.value = "알 수 없는 오류 발생"
        }
    }
}