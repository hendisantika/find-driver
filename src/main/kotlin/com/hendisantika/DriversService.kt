package com.hendisantika

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.transaction.annotation.Transactional


/**
 * Created by hendisantika on 15/02/17.
 */
@Transactional
interface DriversDao : PagingAndSortingRepository<Drivers, Long>, JpaSpecificationExecutor<Drivers> {
    @Transactional(timeout = 10)
    fun findByLatitudeAndLongitudeAndDistance(lat: Double, long: Double, radius: Int?, pageable: Pageable?): List<Drivers>

    override fun findAll(pageable: Pageable): Page<Drivers>
}