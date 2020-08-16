package net.testusuke.azisaba.itemtransporter

import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException

object Base64Converter {

    fun toBase64(itemStack: ItemStack): String? {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)
            dataOutput.writeInt(1)
            dataOutput.writeObject(itemStack)
            dataOutput.close()
            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            throw IllegalStateException("Unable to save item stacks.", e)
        }
    }
    @Throws(IOException::class)
    fun fromBase64(data: String?): ItemStack? {
        return try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)
            val itemStack = dataInput.readObject() as ItemStack
            dataInput.close()
            itemStack
        } catch (e: ClassNotFoundException) {
            throw IOException("Unable to decode class type.", e)
        }
    }
}