package com.mecm.moneybag.utils

import android.os.Environment
import com.mecm.initandroid.utils.SwmLogUtils
import java.io.*

class SwmFileUtil {
    var sdpath: String? = null

    constructor() {
        //得到外部存储设备的目录（/SDCARD）
        sdpath = Environment.getExternalStorageDirectory().toString() + "/"
    }

    /**
     * 在SD卡上创建文件
     *
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    @Throws(IOException::class)
    fun createSDFile(fileName: String): File {
        val file = File(sdpath!! + fileName)
        file.createNewFile()
        return file
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName 目录名字
     * @return 文件目录
     */
    fun createDir(dirName: String): File {
        val dir = File(sdpath!! + dirName)
        dir.mkdir()
        return dir
    }

    companion object {
        /**
         * 在SD卡上创建目录
         * @param dirName 目录名字
         * @return 文件目录
         */
        fun createDirBy(dirName: String): File {
            val dir = File(dirName)
            dir.mkdir()
            return dir
        }

    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    fun isFileExist(fileName: String): Boolean {
        val file = File(sdpath!! + fileName)
        return file.exists()
    }

    fun write2SDFromInput(path: String, fileName: String, input: InputStream, totalLength: Long): File? {
        var file: File? = null
        var output: OutputStream? = null
        var currentLength: Long = 0

        try {
            createDir(path)
            file = createSDFile(path + fileName)
            output = FileOutputStream(file)
            val buffer = ByteArray(4 * 1024)
            var len: Int
            do {
                len = input.read(buffer)
                if (len != -1) {
                    currentLength += len
                    SwmLogUtils.e("现在的进度=" + currentLength)
                    SwmLogUtils.e("百分比=" + currentLength * 100 / totalLength)

                    output.write(buffer, 0, len)
                    output.flush()
                } else {
                    break
                }
            } while (true)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                output!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return file
    }
}