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

    @SuppressLint("CheckResult")
    private fun insert() {
        Observable
                .create(object : ObservableOnSubscribe<Object> {
                    override fun subscribe(emitter: ObservableEmitter<Object>) {
                        // 增加用户
                        var user = User()
                        user.name = "用户A"
                        user.age = 20
                        userDao.insert(user)
                        emitter.onNext(object : Object() {

                        })
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(object : Consumer<Object> {
                    override fun accept(t: Object?) {
                        show()
                    }
                })
    }

    @SuppressLint("CheckResult")
    private fun delete() {
        Observable
                .create(object : ObservableOnSubscribe<List<User>> {
                    override fun subscribe(emitter: ObservableEmitter<List<User>>) {
                        var users = userDao.getAllUsers()
                        userDao.delete(users[0])
                        emitter.onNext(users)
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(object : Consumer<List<User>> {
                    override fun accept(t: List<User>) {
                        show()
                    }
                })
    }

    @SuppressLint("CheckResult")
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
                }).subscribeOn(Schedulers.io())
                .subscribe(object : Consumer<List<User>> {
                    override fun accept(t: List<User>?) {
                        show()
                    }
                })
    }

    @SuppressLint("CheckResult")
    private fun show() {
        tv_content.text = ""
        Flowable
                .fromIterable(userDao.getAllUsers())
                .map(object : Function<User, String> {
                    override fun apply(t: User): String {
                        return t.toString()
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String?) {
                        tv_content.append(t)
                        tv_content.append("\n")
                    }
                })
    }
}