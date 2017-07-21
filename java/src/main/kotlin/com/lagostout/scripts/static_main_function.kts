object O {
    @JvmStatic
    fun main(args: Array<String>) { }
}

class C {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) { }
    }
}

fun main(vararg args: Array<String>) { println(args) }
main()
