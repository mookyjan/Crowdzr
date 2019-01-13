package com.example.mudassirkhan.crowdzr.api.errorhandling

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
 data  class NetworkState private constructor(
        val status: Status,
        val msg: String? = null) {
    companion object {
        public val LOADED = NetworkState(Status.SUCCESS)
        public val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}