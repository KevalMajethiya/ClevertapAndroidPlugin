package action

object d {
    @JvmStatic
    fun main(args: Array<String>) {
        val str = "manifest xmlns:android=\"http://schemas.android.com/apk/res/android\""
        val parts = str.split(" ").toTypedArray()
        for (part in parts) {
            val word = removeChars(part)
            println(word)
        }
    }

    private fun removeChars(part: String): String {
        var word = part
        if (part.endsWith(".") || part.endsWith("?") || part.endsWith(",")) {
            word = part.substring(0, part.length - 1)
        }
        if (part.startsWith("\"") && part.endsWith("\"")) {
            word = part.substring(1, part.length - 1)
        }
        return word
    }
}