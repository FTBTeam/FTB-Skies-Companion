architectury {
    fabric()
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentFabric"].extendsFrom(this)
}

dependencies {
    common(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(path = ":common", configuration = "transformProductionFabric")) {
        isTransitive = false
    }

    val minecraftVersion: String by project
    val fabricLoaderVersion: String by project
    val fabricApiVersion: String by project
    val modMenuVersion: String by project
    val createFabricVersion: String by project
    val reiVersion: String by project
    val patchouliVersion: String by project

    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = fabricLoaderVersion)
    modApi(group = "net.fabricmc.fabric-api", name = "fabric-api", version = "$fabricApiVersion+$minecraftVersion")
    modRuntimeOnly(group = "me.shedaniel", name = "RoughlyEnoughItems-fabric", version = reiVersion)

    modApi(group = "com.terraformersmc", name = "modmenu", version = modMenuVersion)

    modImplementation(group = "com.simibubi.create", name = "create-fabric-$minecraftVersion", version = createFabricVersion)
    modImplementation("com.github.AlphaMode:fakeconfig:master-SNAPSHOT") { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation("com.github.AlphaMode:fakeconfigtoml:master-SNAPSHOT") { exclude(group = "net.fabricmc.fabric-api") }


    modRuntimeOnly("vazkii.patchouli:Patchouli:$minecraftVersion-$patchouliVersion-FABRIC")
}
