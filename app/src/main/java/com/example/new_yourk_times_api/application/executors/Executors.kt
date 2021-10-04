package com.example.new_yourk_times_api.application.executors

import java.util.concurrent.Executor

class Executors (
    val mainThread: Executor,
    val background: Executor
    )