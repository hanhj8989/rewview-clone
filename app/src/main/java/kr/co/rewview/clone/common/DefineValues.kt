package kr.co.rewview.clone.common

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import kr.co.rewview.clone.BuildConfig
//import kr.co.rewview.clone.info.MagazineInfoV2
//import kr.co.rewview.clone.info.UserInfo
//import kr.co.rewview.clone.info.UserInfoV2

/**
 * Created by Administrator on 2019-07-08.
 */

object DefineValues {
    var USE_DEBUG = false

    const val DEVICE_TYPE = "A"
    const val PROPERTY_RECENT_KEYWORD_COUNT = "recent_keyword_count"
    const val PROPERTY_RECENT_KEYWORD = "recent_keyword"
    const val PROPERTY_REQUEST_TOKEN = "request_token"                             // 2021/09/24 seongtaek
    const val PROPERTY_CAMPAIGN_VIEW_HISTORY = "campaign_view_history"             // 지난 3일간 봤던 캠페인 저장. 2022/08/04 seongtaek
    const val PROPERTY_LAST_NOTICE_IDX = "last_notice_idx"                         // 내가 마지막 열람한 공지사항 2022/10/24 seongtaek
    const val NAVER_HTML = "naver_html"                                            // 2022/06/02 seongtaek
    const val PROPERTY_USER_REPORT_HISTORY = "user_report_history"                 // 앱을 시작한 이후에 신고한 유저 리스트 2023/08/19 seongtaek

    const val ZONE_CODE = "zone_code"
    const val ADDRESS = "address"
    const val SIDO = "sido"       // 광역단체2023/04/12 seongtaek
    const val USER_DUPLICATION = "user_duplication"
    const val MOBILE = "mobile"
    const val NAME = "name"

    const val MEDIA_TYPE_GQ = "GQ"
    const val MEDIA_TYPE_VOGUE = "Vogue"
    const val MEDIA_TYPE_ALLURE = "Allure"
    const val MEDIA_TYPE_W = "W"
    const val MEDIA_TYPE_REWVIEW = "Rewview"
    const val MEDIA_TYPE_GREEN_ALLURE = "Green_allure"

    const val PARTNER_URL: String = "http://partner.rewview.co.kr"
    const val BUSINESS_INFO_URL: String = "http://www.ftc.go.kr/bizCommPop.do?wrkr_no=2118551635"


    //******************** 웹 URL start ********************
    // 공유 URL 변경. 기존 공유 URL은 모두 WV_SHARE 로 변경. 2022/09/16 seongtaek






    const val REQUEST_CART = 0xFFFF
    const val REFRESH_TOKEN = "tokenReceiver"
    const val REQUEST_VIEW_SPECIAL_REVIEW                  = 1001
    const val REQUEST_VIEW_CONTENT_COMMENT                 = 1002
    const val REQUEST_MODIFY_REVIEW                        = 1003
    const val REQUEST_WRITE_PROFILE                        = 1004
    const val REQUEST_VIEW_TALK_OUR_PREVIOUS               = 1005
    const val REQUEST_CAMPAIGN_CODE                        = 1006
    const val REQUEST_PHOTO_SELECT_CODE                    = 1007
    const val REQUEST_REVIEW_CODE                          = 1008
    const val REQUEST_SEARCH_CODE                          = 1009
    const val REQUEST_MAGAZINE_CODE                        = 1010
    const val REQUEST_PERMISSION_CODE                      = 1011
    const val REQUEST_WRITE_DOCUMENT                       = 1012
    const val REQUEST_TALK_CODE                            = 1013
    const val REQUEST_GALLERY_CODE                         = 1014
    const val REQUEST_FILTER_CODE                          = 1015
    const val REQUEST_VIEW_EXHIBITION_CODE                 = 1016
    const val REQUEST_REVIEW_FILTER                        = 1017     // 2020/09/27 seongtaek 필터 화면으로 이동하는 requestCode 추가.
    const val REQUEST_MODIFY_TALK_OUR                      = 1018
    const val REQUEST_NAVER_BLOG_CONNECT                   = 1019
    const val REQUEST_CHECK_POSTING_GUIDE                  = 1020     // 2022/05/02 seongtaek
    const val REQUEST_DAUM_POST                            = 1021     // 2023/04/18 seongtaek
    const val REQUEST_ACCOUNT_REGISTRATION                 = 1022     // 2023/06/08 seongtaek
    const val REQUEST_INSTAGRAM_CONNECT                    = 1023     // 2023/11/03 seongtaek
    const val REQUEST_MISSION_PROGRESS                     = 1024     // 2024. 8. 14. seongtaek
    const val REQUEST_CAMPAIGN_VIEW                        = 1025     // 2024. 8. 14. seongtaek
    const val REQUEST_NOTICE                               = 1026
    const val REQUEST_SELF_AUTH                            = 1027
    const val REQUEST_SNS_CONNECT                          = 1028
    const val REFRESH_ITEM                                 = 2000
    const val VIEW_IMAGE                                   = 3000



    const val RECENT_KEYWORD_MAX_COUNT = 10

    @Deprecated("서비스 개편이후로 USER_INFO_V2 를 사용")
    //var USER_INFO = UserInfo()

   // var USER_INFO_V2 = UserInfoV2()            // 서비스 개편 (4.0.0) 이후 부터 UserInfo 대신 UserInfoV2 사용 2024-08-29
    // seongtaek
    //var MAGAZINE_INFO: MagazineInfoV2? = null       // 매거진 리스트에서 갱신을 위해 저장.

    open var LOADING_ANIMATION = AnimationDrawable()
    open var USER_LEVEL_DRAWABLE = ArrayList<Drawable>()
}
