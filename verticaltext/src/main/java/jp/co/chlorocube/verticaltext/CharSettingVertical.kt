package jp.co.chlorocube.verticaltext

import java.nio.charset.Charset

/**
 * こちらのサイト様を参考にさせていただきました。
 * 厚く御礼申し上げます。
 * https://tomorrowkey-2.hatenadiary.org/entry/20110123/1295747338
 */
class CharSettingVertical(
    val character: String,
    val angle: Float,
    val x: Float, // -0.5fが設定された場合、1/2文字分左にずれる
    val y: Float, // -0.5fが設定された場合、1/2文字分上にずれる
    val inversion: Boolean = false
) {

    companion object {
        private val settings = arrayOf(
            CharSettingVertical("、", 0.0f, 0.7f, -0.6f),
            CharSettingVertical("。", 0.0f, 0.7f, -0.6f),
            CharSettingVertical("ー", 90.0f, -0.1f, 0.9f, true),
            CharSettingVertical("～", -90.0f, -0.1f, 0.9f, true),
            CharSettingVertical("〜", -90.0f, -0.1f, 0.9f, true),
            CharSettingVertical("…", 90.0f, -0.85f, -0.2f),
            CharSettingVertical("―", 90.0f, -0.85f, -0.1f),
            CharSettingVertical("＝", 90.0f, -0.85f, -0.1f),

            CharSettingVertical(":", 90.0f, 0.0f, -0.1f),
            CharSettingVertical(";", 90.0f, 0.0f, -0.1f),

            CharSettingVertical("「", 90.0f, -0.9f, -0.3f),
            CharSettingVertical("」", 90.0f, -0.8f, -0.0f),
            CharSettingVertical("『", 90.0f, -0.9f, -0.3f),
            CharSettingVertical("』", 90.0f, -0.8f, -0.0f),
            CharSettingVertical("（", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("）", 90.0f, -0.8f, -0.15f),
            CharSettingVertical("〈", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("〉", 90.0f, -0.8f, -0.15f),
            CharSettingVertical("【", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("】", 90.0f, -0.8f, -0.15f),
            CharSettingVertical("《", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("》", 90.0f, -0.8f, -0.15f),
            CharSettingVertical("｛", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("｝", 90.0f, -0.8f, -0.15f),
            CharSettingVertical("［", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("］", 90.0f, -0.8f, -0.15f),
            CharSettingVertical("〔", 90.0f, -0.9f, -0.15f),
            CharSettingVertical("〕", 90.0f, -0.8f, -0.15f),

            CharSettingVertical("ぁ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ぃ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ぅ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ぇ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ぉ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ゃ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ゅ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ょ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("っ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ァ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ィ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ゥ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ェ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ォ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ッ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ャ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ュ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ョ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ヵ", 0.0f, 0.1f, -0.1f),
            CharSettingVertical("ヶ", 0.0f, 0.1f, -0.1f),
        )
        private val halfSettings = createHalfSettings()

        private fun createHalfSettings(): Array<CharSettingVertical> {

            val list = mutableListOf<CharSettingVertical>()
            for (i in 33..126) { // ASCII
                list.add(
                    CharSettingVertical(
                        String(byteArrayOf(i.toByte()), Charset.defaultCharset()),
                        90.0f, -0.6f, -0.1f
                    )
                )
            }
            return list.toTypedArray()
        }

        fun getSetting(character: String): CharSettingVertical? {

            for (i in halfSettings.indices) {
                if (halfSettings[i].character == character)
                    return halfSettings[i]
            }
            for (i in settings.indices) {
                if (settings[i].character == character)
                    return settings[i]
            }
            return null
        }
    }
}