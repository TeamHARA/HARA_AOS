package com.android.hara.data.repository

import com.android.hara.data.datasource.HaraAloneService
import com.android.hara.data.datasource.HaraWithService
import com.android.hara.domain.repository.HaraAloneRepository
import com.android.hara.domain.repository.HaraWithRepository
import javax.inject.Inject

class HaraWithRepositoryImpl @Inject constructor(
    private val HaraWithService: HaraWithService
) : HaraWithRepository {
}