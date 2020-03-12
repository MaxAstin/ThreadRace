import java.io.*

fun main() {
    val file = File("race_log.txt")
    file.writeBytes("Ready, set, go! \n".toByteArray())

    val firstRacer = ThreadRacer("first racer")
    val secondRacer = ThreadRacer("second racer")
    firstRacer.rival = secondRacer
    secondRacer.rival = firstRacer

    firstRacer.start()
    secondRacer.start()
}

class ThreadRacer(private val racerName: String) : Thread() {
    private val file = File("race_log.txt")
    lateinit var rival: ThreadRacer

    override fun run() {
        for (i in 1..100) {
            file.appendBytes("$racerName : $i \n".toByteArray())
        }

        if (rival.isAlive) {
            file.appendBytes("$racerName : I won! \n".toByteArray())
        } else {
            file.appendBytes("$racerName : I lost! \n".toByteArray())
        }
    }
}
