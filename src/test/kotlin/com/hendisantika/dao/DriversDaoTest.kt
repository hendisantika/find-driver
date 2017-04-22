package com.hendisantika.dao

import com.hendisantika.model.Drivers
import com.hendisantika.service.DriversDao
import mu.KotlinLogging
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import javax.sql.DataSource

/**
 * Created by hendisantika on 16/02/17.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = arrayOf("/sql/clean.sql", "/sql/drivers.sql"))
class DriversDaoTest {

    @Autowired
    lateinit var dd: DriversDao

    @Autowired
    lateinit var ds: DataSource

    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    private val restTemplate = TestRestTemplate()

//    companion object : KLogging()
    private val logger = KotlinLogging.logger {}

    @Test
    fun testSimpan() {
        val d = Drivers()
        d.id = 11
        d.latitude = 1.1
        d.longitude = 100.0
        d.distance = 500

        dd.save(d)

        logger.info("Data sukses tersimpan!")
        // test query
        Assert.assertNotNull(d.id)
        logger.info("ID Driver baru : " + d.id)
        val px = dd.findOne(d.id)
        Assert.assertNotNull("11", px.id)
    }


    @Test
    fun testCariById() {
        val d1 = dd.findOne(200)
        Assert.assertNull(d1)
        logger.info("Data Tidak ada!")

        val p = dd.findOne(2)
        Assert.assertNotNull(p)
        logger.info("Data " + p.id + " ditemukan!")
        Assert.assertEquals(2.0, p.latitude)
        Assert.assertNotNull(p.longitude)
        Assert.assertEquals(20.0, p.longitude)
    }

    @After
    @Throws(Exception::class)
    fun hapusData() {
        val sql = "TRUNCATE TABLE drivers"
        val sql2 = "ALTER TABLE drivers AUTO_INCREMENT = 1"
        val connection = ds.connection
        try {
            connection.createStatement().executeUpdate(sql)
            connection.createStatement().executeUpdate(sql2)
            logger.info("Data sudah terhapus!")
        } finally {
            connection.close()
        }
    }

    @Test
    fun testHitung() {
        val jumlah = dd.count()
        Assert.assertEquals(10L, jumlah!!.toLong())
        logger.info("Jumlah --> " + jumlah)
    }

    @Test
    fun testCariByLatitude() {

        val selectQuery = "SELECT * from drivers WHERE latitude = 2.0"

        val resultSet = jdbcTemplate!!.queryForList(selectQuery)

        logger.info("Datanya ....")
        logger.info(resultSet.toString())
    }


//    @Test
//    fun getDriver() {
//        val driverUrl = "http://localhost:8080/drivers?latitude=1&longitude=10"
//
//        val responseType = object : ParameterizedTypeReference<Resource<Drivers>>() {}
//
//        val responseEntity = restTemplate.exchange(driverUrl, HttpMethod.GET, null, responseType)
//
//        val driver = responseEntity.getBody().getContent()
//        assertEquals("1", driver.latitude)
//    }


}
