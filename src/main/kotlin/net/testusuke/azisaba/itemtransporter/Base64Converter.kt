package net.testusuke.azisaba.itemtransporter

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException

object Base64Converter {
    fun toBase64(inventory: Inventory): String? {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)

            // Write the size of the inventory
            dataOutput.writeInt(inventory.size)

            // Save every element in the list
            for (i in 0 until inventory.size) {
                dataOutput.writeObject(inventory.getItem(i))
            }

            // Serialize that array
            dataOutput.close()
            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            throw IllegalStateException("Unable to save item stacks.", e)
        }
    }

    @Throws(IOException::class)
    fun fromBase64(data: String?): Inventory? {
        return try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)
            val inventory = Bukkit.getServer().createInventory(null, dataInput.readInt())

            // Read the serialized inventory
            for (i in 0 until inventory.size) {
                inventory.setItem(i, dataInput.readObject() as ItemStack)
            }
            dataInput.close()
            inventory
        } catch (e: ClassNotFoundException) {
            throw IOException("Unable to decode class type.", e)
        }
    }
}