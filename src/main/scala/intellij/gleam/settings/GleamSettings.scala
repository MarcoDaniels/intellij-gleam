package main.scala.intellij.gleam.settings

import com.intellij.openapi.components.State.NameGetter
import com.intellij.openapi.components.{
  PersistentStateComponent,
  State,
  Storage,
  StoragePathMacros
}
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

import scala.beans.BeanProperty

@State(
  name = "GleamLSPSettings",
  storages = Array(
    new Storage(StoragePathMacros.WORKSPACE_FILE),
    new Storage("gleam-idea-tools.xml")
  ),
  presentableName = classOf[GleamSettingsNameGetter]
)
class GleamSettings extends PersistentStateComponent[GleamSettings] {
  override def getState: GleamSettings = this

  override def loadState(state: GleamSettings): Unit =
    XmlSerializerUtil.copyBean(state, this)

  @BeanProperty var enableLSP = false
  @BeanProperty var executable = ""
}

object GleamSettings {
  def getInstance(project: Project): GleamSettings =
    project.getService(classOf[GleamSettings])
}

class GleamSettingsNameGetter extends NameGetter {
  override def get(): String = "Gleam Project Settings"
}
