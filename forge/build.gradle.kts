architectury {
    forge()
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentForge"].extendsFrom(this)
}

dependencies {
    common(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(path = ":common", configuration = "transformProductionForge")) {
        isTransitive = false
    }

    val minecraftVersion: String by project
    val forgeVersion: String by project
    val createVersion: String by project
    val flywheelVersion: String by project
    val registrateVersion: String by project
    val curiosVersion: String by project
    val jeiVersion: String by project
    val patchouliVersion: String by project

    forge(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")

    modImplementation("com.simibubi.create:create-${minecraftVersion}:${createVersion}:slim") { isTransitive = false }
    modImplementation(group = "com.jozufozu.flywheel", name = "flywheel-forge-${minecraftVersion}", version = flywheelVersion)
    modImplementation(group = "com.tterrag.registrate", name = "Registrate", version = registrateVersion)

    // modImplementation(group = "mezz.jei", name = "jei-$minecraftVersion-forge", version = jeiVersion)

    modRuntimeOnly("top.theillusivec4.curios:curios-forge:${curiosVersion}")
    modCompileOnly("top.theillusivec4.curios:curios-forge:${curiosVersion}:api")
    modRuntimeOnly("vazkii.patchouli:Patchouli:$minecraftVersion-$patchouliVersion")

}
