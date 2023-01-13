package com.android.hara.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.VoteResDto
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val haraRepository: HARARepository) : ViewModel() {

    private var votePostId: Int = -1 // [홈화면: 옵션 선택해 투표] 글 id
    private var voteOptId: Int = -1 // [홈화면: 옵션 선택해 투표] 옵션 id

    // [홈화면: 투표] post 통신 결과
    private val _voteResult: MutableLiveData<VoteResDto> = MutableLiveData()
    val voteResult: LiveData<VoteResDto> = _voteResult

    /* [홈화면: 고민글 목록 전체조회] selected category number */
    private val _selCat: MutableLiveData<Int> = MutableLiveData()
    val selCat: LiveData<Int> = _selCat

    /* [홈화면: 옵션 선택해 투표] 투표하기 버튼 - true면 post 통신을 하자 */
    private val _btnSel: MutableLiveData<Boolean> = MutableLiveData()
    val btnSel: LiveData<Boolean> = _btnSel

    // [홈화면: 고민글 목록 전체조회] category 별 모든 글 목록
    private val _catAllPostResult: MutableLiveData<AllPostResDto> = MutableLiveData()
    val catAllPostResult: LiveData<AllPostResDto> = _catAllPostResult

    // [홈화면: 고민글 목록 전체조회] 내가 쓴 글 목록
    private lateinit var myPostResult: List<AllPostResDto.Data>

    // [홈화면: 고민글 목록 전체조회] 남이 쓴 글 목록
    private lateinit var otherPostResult: List<AllPostResDto.Data>

    // 서버통신 결과
    private val _success = MutableLiveData<Boolean>()
    val suceess get() = _success

    /* 서버통신 성공/실패 시 어떤 작업을 해야 하는지 정의한다 */

    init {
        viewModelScope.launch {
            runCatching {
                haraRepository.getAllPost(0)
            }.onSuccess {
                if (it.isSuccessful) { // 내부 코드의 응답코드 200~299
                    Timber.e("Success")
                    _catAllPostResult.value = it.body()
                    /*
                    // [홈화면: 고민글 목록 전체조회] 내가 쓴 글 목록 (필터링)
                    myPostResult = _catAllPostResult.value!!.data.filter {
                        it.isAuthor
                    }

                    // [홈화면: 고민글 목록 전체조회] 남이 쓴 글 목록 (필터링)
                    otherPostResult = _catAllPostResult.value!!.data.filter {
                        !it.isAuthor
                    }
                    */
                } else { // 응답코드 400~599
                    Timber.e("서버통신 응답코드 이상")
                }
            }.onFailure { // 서버통신 자체가 실패했다
                Timber.e(it)
                Timber.e("서버통신 실패", it)
            }
        }
    }

    /*----------------------------------------------------------*/

    fun changeSelCatNum(n: Int) {
        _selCat.value = n
    }


    fun changeSelPostAndOptId(postId: Int, optId: Int) {
        votePostId = postId
        voteOptId = optId
    }

    fun changeBtnVal() {
        _btnSel.value = !(_btnSel.value)!!
    }

    fun getPostId(): Int {
        return votePostId
    }

    fun getOptId(): Int {
        return voteOptId
    }

    fun getOptVoteRate(): List<VoteResDto.Data.Option>? {
        for (i in 0..(_voteResult.value?.data?.size?.minus(1) ?: 0)) {
            if (this.votePostId == _voteResult.value?.data?.get(i)?.worryId)
                return _voteResult.value?.data?.get(i)?.option
        }
        return emptyList()
    }

    /*----------------------------------------------------------*/
    /* 서버통신의 결과인 response를 담는다 */


    /*----------------------------------------------------------*/

    // [홈화면: 고민글 목록 전체조회] category id를 쿼리로 보내는 get 통신
    fun homeVmGetAllPost(n: Int) {
        viewModelScope.launch {
            runCatching {
                haraRepository.getAllPost(n)
            }.onSuccess {
                if (it.isSuccessful) { // 내부 코드의 응답코드 200~299
                    Timber.e("Success")
                    _success.value = true
                    _catAllPostResult.value = it.body()
                } else { // 응답코드 400~599
                    _success.value = false
                    Timber.e("서버통신 응답코드 이상")
                }
            }.onFailure { // 서버통신 자체가 실패했다
                Timber.e(it)
                _success.value = false
                Timber.e("서버통신 실패", it)
            }
        }
    }

    // [홈화면: 투표] post 통신
    fun homeVmPostVote(postId: Int, optId: Int) {
        viewModelScope.launch {
            runCatching {
                haraRepository.postVote(VoteReqDto(postId, optId))
            }.onSuccess {
                if (it.isSuccessful) { // 내부 코드의 응답코드 200~299
                    _voteResult.value = it.body()
                    Timber.e("Success")
                } else { // 응답코드 400~599
                    Timber.e("서버통신 응답코드 이상")
                }
            }.onFailure { // 서버통신 자체가 실패했다
                Timber.e(it)
                Timber.e("서버통신 실패", it)
            }
        }
    }
}
