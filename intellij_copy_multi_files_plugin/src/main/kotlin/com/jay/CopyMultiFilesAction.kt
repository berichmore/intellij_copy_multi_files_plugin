package com.jay

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.util.io.FileUtil
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection


class CopyMultiFilesAction : AnAction() {


    override fun getActionUpdateThread(): ActionUpdateThread {
        //추후 업데이트를 위함 EDT 사용
        return ActionUpdateThread.BGT
    }
        //트러블 슈팅 - 1
    override fun update(e: AnActionEvent) {
        //여러 파일이 선택되었는지, 디렉토리만 선택된 것은 아닌지 체크해서
        //이 액션을 우클릭 메뉴에서 보이게/ 안 보이게 할 수 있다.
        val files = e.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY)
        //파일이 있고, 그 중 하나 이상이 디렉토리가 아닌 파일이면 true
        e.presentation.isEnabledAndVisible = !files.isNullOrEmpty() && files.any { !it.isDirectory }
    }


    override fun actionPerformed(e: AnActionEvent) {
        val files = e.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY) ?: return

        //각 파일의 내용을 읽어서 하나의 문자열로 결합

        val combinedText = buildString {
            files.forEachIndexed { index, file ->
                //디렉토리는 패스
                if (!file.isDirectory) {

                    // 리팩토링 전 - 파일 내용을 읽기 (Fileutil.LoadFile() 사용 예)
//                    val fileOnDisk = file.toNioPath().toFile()
//                    val content: String = FileUtil.loadFile(fileOnDisk, file.charset.name())

                    // 리팩토링 후 : Intelli J 권장방식 설정
                    val content: String = try {
                        //성공 후 실행 코드
                        file.inputStream.bufferedReader(file.charset).use { reader ->
                            reader.readText()
                        }
                    } catch (e: Exception) {
                            //파일 읽기 실패 시 예외 처리
                            "[ERROR: Could not read file '${file.name}']"
                    }
                    //파일 제목 + 내용 구분 선
                    appendLine("===== File #${index + 1}: ${file.name} =====")
                    appendLine(content)
                    appendLine()
                }
            }
        }

        //클립보드에 복사
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(StringSelection(combinedText), null)

        //또는 IntelliJ CopyPasteManager 쓸 수도 있음
//        CopyPasteManager.getInstance().setContents(StringSelection(combinedText))

    }
}


