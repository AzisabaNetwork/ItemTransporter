package net.testusuke.azisaba.itemtransporter

/**
 * Created by testusuke on 2020/08/30
 * @author testusuke
 */
sealed class ResultType {
    data class Success(val amount: Int) : ResultType()
    data class Error(val reason: ErrorReason) : ResultType()
}

enum class ErrorReason {
    CAN_NOT_ACCESS_DB,
    FAILED_SQL,
    ITEM_NOT_FOUND
}