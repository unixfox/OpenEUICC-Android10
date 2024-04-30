package im.angry.openeuicc.util

import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import com.truphone.lpa.LocalProfileInfo
import java.lang.Exception

val TelephonyManager.supportsDSDS: Boolean
    get() {
        return false
    }

var TelephonyManager.dsdsEnabled: Boolean
    get() {
        return false
    }
    set(value) {}

fun SubscriptionManager.tryRefreshCachedEuiccInfo(cardId: Int) {
    if (cardId != 0) {
        try {
            requestEmbeddedSubscriptionInfoListRefresh(cardId)
        } catch (e: Exception) {
            // Ignore
        }
    }
}

val LocalProfileInfo.displayName: String
    get() = nickName.ifEmpty { name }

val List<LocalProfileInfo>.operational: List<LocalProfileInfo>
    get() = filter {
        it.profileClass == LocalProfileInfo.Clazz.Operational
    }