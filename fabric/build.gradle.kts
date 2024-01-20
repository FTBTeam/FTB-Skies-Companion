architectury {
    fabric()
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentFabric"].extendsFrom(this)
}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:0.15.0")
    }
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
    val jeiVersion: String by project
    val patchouliVersion: String by project

    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = fabricLoaderVersion)
    modApi(group = "net.fabricmc.fabric-api", name = "fabric-api", version = "$fabricApiVersion+$minecraftVersion")
    // modRuntimeOnly(group = "me.shedaniel", name = "RoughlyEnoughItems-fabric", version = reiVersion)
    modImplementation(group = "mezz.jei", name = "jei-$minecraftVersion-forge", version = jeiVersion)

    modApi(group = "com.terraformersmc", name = "modmenu", version = modMenuVersion)

    modImplementation(group = "com.simibubi.create", name = "create-fabric-$minecraftVersion", version = createFabricVersion)
    modImplementation("com.github.AlphaMode:fakeconfig:master-SNAPSHOT") { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation("com.github.AlphaMode:fakeconfigtoml:master-SNAPSHOT") { exclude(group = "net.fabricmc.fabric-api") }


    modRuntimeOnly("vazkii.patchouli:Patchouli:$minecraftVersion-$patchouliVersion-FABRIC")
}
