package com.lingxiao.thefirst.mine.jetpack.room

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_jetpack_room.*

/**
 * @author luckw
 * @date   2020/5/25
 */

class JetpackRoomActivity : BaseActivity() {
    lateinit var userDao: UserDao


    override fun initView(savedInstanceState: Bundle?) {
        insert.setOnClickListener(this)
        delete.setOnClickListener(this)
        update.setOnClickListener(this)

    }

    override fun initData() {
        super.initData()
        userDao = AppDataBase.getInstance(this).userDao()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_jetpack_room
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.insert ->
                insert()
            R.id.delete ->
                delete()
            R.id.update ->
                update()
        }
    }

    private fun insert() {
        Observable
                .create(object : ObservableOnSubscribe<List<User>> {
                    override fun subscribe(emitter: ObservableEmitter<List<User>>) {
                        // 增加用户
                        var user = User()
                        user.name = "用户A"
                        user.age = 20
                        userDao.insert(user)
                        emitter.onNext(userDao.getAllUsers())
                    }
                }).flatMap(object : Function<List<User>, ObservableSource<User>> {
                    override fun apply(list: List<User>): ObservableSource<User> {
                        return Observable.fromIterable(list)
                    }
                }).map(object : Function<User, String> {
                    override fun apply(user: User): String {
                        return user.toString()
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                    }
                    override fun onSubscribe(d: Disposable) {
                        //TODO 注意onSubscribe方法只执行一次
                        tv_content.text = ""
                    }
                    override fun onNext(t: String) {
                        tv_content.append(t)
                        tv_content.append("\n")
                    }
                    override fun onError(e: Throwable) {
                    }
                })
    }

    private fun delete() {
        Observable
                .create(object : ObservableOnSubscribe<List<User>> {
                    override fun subscribe(emitter: ObservableEmitter<List<User>>) {
                        var users = userDao.getAllUsers()
                        userDao.delete(users[0])
                        emitter.onNext(userDao.getAllUsers())
                    }
                })
                .flatMap(object : Function<List<User>, ObservableSource<User>> {
                    override fun apply(t: List<User>): ObservableSource<User> {
                        return Observable.fromIterable(t)
                    }
                })
                .map(object : Function<User, String> {
                    override fun apply(t: User): String {
                        return t.toString()
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                    }
                    override fun onSubscribe(d: Disposable) {
                        tv_content.text = ""
                    }
                    override fun onNext(t: String) {
                        tv_content.append(t)
                        tv_content.append("\n")
                    }
                    override fun onError(e: Throwable) {
                    }
                })
    }

    private fun update() {
        Observable
                .create(object : ObservableOnSubscribe<List<User>> {
                    override fun subscribe(emitter: ObservableEmitter<List<User>>) {
                        var users = userDao.getAllUsers()
                        users[0].name = "用户B"
                        users[0].age = 30
                        userDao.update(users[0])
                        emitter.onNext(users)
                    }
                }).flatMap { Observable.fromIterable(it) }
                .map { it.toString() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                    }
                    override fun onSubscribe(d: Disposable) {
                        tv_content.text = ""
                    }
                    override fun onNext(t: String) {
                        tv_content.append(t)
                        tv_content.append("\n")
                    }
                    override fun onError(e: Throwable) {
                    }
                })
    }
}