package com.example.charactersdisney.common

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {
    val isConnected: Flow<Boolean>
}