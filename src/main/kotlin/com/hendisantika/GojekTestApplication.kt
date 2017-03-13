package com.hendisantika

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@SpringBootApplication
@EnableAsync
class GojekTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(GojekTestApplication::class.java, *args)
}

fun getAsyncExecutor(): Executor {
    val executor = ThreadPoolTaskExecutor()
    executor.corePoolSize = 2
    executor.maxPoolSize = 2
    executor.setQueueCapacity(500)
    executor.threadNamePrefix = "GithubLookup-"
    executor.initialize()
    return executor
}

@Bean
fun init(repository: DriversDao) = CommandLineRunner {
    repository.save(Drivers(1, 6.10212,105.10212,100))
    repository.save(Drivers(2, 6.2010212,105.2010212,150))
            repository.save(Drivers(3, 6.3010212,105.3010212,150))
            repository.save(Drivers(4, 6.4010212,105.4010212,120))
            repository.save(Drivers(5, 6.5010212,105.5010212,200))
            repository.save(Drivers(6, 6.6010212,105.6010212,300))
            repository.save(Drivers(7, 6.7010212,105.7010212,250))
            repository.save(Drivers(8, 6.8010212,105.8010212,175))
            repository.save(Drivers(9, 6.9010212,105.9010212,400))
            repository.save(Drivers(10, 6.411212,105.11212,300))
            repository.save(Drivers(11, 6.122127,105.127212,350))
            repository.save(Drivers(12, 6.132120,105.132120,350))
            repository.save(Drivers(13, 6.142120,105.142120,400))
            repository.save(Drivers(14, 6.152120,105.152120,500))
            repository.save(Drivers(15, 6.162120,105.162120,500))
            repository.save(Drivers(16, 6.172120,105.172120,400))
            repository.save(Drivers(17, 6.182120,105.182120,400))
            repository.save(Drivers(18, 6.192120,105.192120,500))
            repository.save(Drivers(19, 6.292120,105.392120,450))
            repository.save(Drivers(20, 6.392120,105.492120,500))

}




