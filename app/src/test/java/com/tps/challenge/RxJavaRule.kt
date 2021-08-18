package com.tps.challenge

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Useful for when unit testing methods which typically execute on a different thread then the main.
 * If your test are not concerned about threading, it's best to apply this rule so everything is
 * executed on the same thread and in sequential order.
 */
class RxJavaRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}
