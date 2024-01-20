architectury {
    val enabledPlatforms: String by rootProject
    common(enabledPlatforms.split(","))
}

dependencies {
    modCompileOnly(group = "tech.thatgravyboat", name = "commonats", version = "2.0")

    val minecraftVersion: String by project
    val createFabricVersion: String by project

    modCompileOnly(group = "com.simibubi.create", name = "create-fabric-$minecraftVersion", version = createFabricVersion)
}
