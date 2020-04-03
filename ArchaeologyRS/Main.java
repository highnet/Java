package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    enum Material{Purpleheart_Wood,Imperial_Iron,Third_Age_Iron,Zarosian_Insignia,Samite_Silk,
        Imperial_Steel,White_Oak,Goldrune,Orthenglass,Vellum,Cadmium_Red,Ancient_Vis,Tyrian_Purple,
        Leathe_Scraps,Chaotic_Brimstone,Demonhide,Eye_Of_Dagon,Hellfire_Metal,Keramos,White_marble,
        Cobalt_Blue,Everlight_Silvthril,Star_Of_Saradomin,Blood_Of_Orcus,Stormguard_Steel,Wings_Of_War,
        Animal_furs,Armadylean_Yellow,Malachite_Green,Mark_Of_The_Kzaj,Vulcanised_Rubber,Warforged_Bronze,
        Fossilissed_Bone,Yubiusk_Clay,Aetherium_Alloy,Quintessence,Soapstone};

    enum Hotspot{Centurion_Remains,Venator_Remains,Legionary_Remains,Castra_Debris,Lodge_Bar_Storage,
        Lodge_Art_Storage,Administratum_Debris,Cultist_Footlocker,Sacrificial_Altar,Promodroi_Remains,
        Dis_Dungeon_Debris,Praesidio_Remains,Monoceros_Remains,Amphitheatre_Debris,Ceramic_Studio_Debris,
        Carcerem_Debris,Stadio_Debris,Inferal_Art,Shaktroth_Remains, Dominion_Games_Podium,Ikovian_Memorial,
        Oikos_Studio_Debris,Kharidet_Chapel_Debris,Pontifex_remains,Tailory_debris,Animal_Trophies,Goblin_Dorm_debris,
        Oikos_fishing_hut_remains,Weapons_research_debris,Orcus_altar,Dis_overspill,Big_high_war_god_shrine,Gravitron_research_debris,
        Acropolis_debris,Armarium_debris,Yubiusk_animal_pen,Keshik_town_debris,Goblin_trainee_remains,Byzorth_remains,
        Destroyed_golem,Kyzaj_Champions_boudior,Culinarum_Debris,Icyene_weapon_rack,Keshik_weapon_rack,
        Hellfire_forge,Warforge_scrap_Pile,Stockpiled_Art,Ancient_magick_munitions,Bibliotheke_debris,
        Chtonian_trophies,Warforge_weapon_rack,Flight_research_debris,Aetherium_forge,
        Praetorian_remains,Bandos_sanctum_debris,Tsutharoth_remains,Optimatoi_remains,Howls_workshop_Debris,
        War_table_debris,Makeshift_pie_oven}

    private static Map<Hotspot, ArrayList<Material>> PopulateDatabase(){

        Map<Hotspot,ArrayList<Material>> database = new HashMap<>();
        ArrayList <Material> materialsList = new ArrayList<>();
        materialsList.add(Material.Imperial_Iron);
        materialsList.add(Material.Purpleheart_Wood);
        database.put(Hotspot.Centurion_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Zarosian_Insignia);
        database.put(Hotspot.Venator_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Samite_Silk);
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Imperial_Steel);
        database.put(Hotspot.Legionary_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Samite_Silk);
        materialsList.add(Material.White_Oak);
        materialsList.add(Material.Zarosian_Insignia);
        materialsList.add(Material.Third_Age_Iron);
        database.put(Hotspot.Castra_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Orthenglass);
        materialsList.add(Material.Third_Age_Iron);
        database.put(Hotspot.Lodge_Bar_Storage,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Samite_Silk);
        materialsList.add(Material.White_Oak);
        materialsList.add(Material.Vellum);
        materialsList.add(Material.Cadmium_Red);
        database.put(Hotspot.Lodge_Art_Storage,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Tyrian_Purple);
        materialsList.add(Material.Ancient_Vis);
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Zarosian_Insignia);
        database.put(Hotspot.Administratum_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Leathe_Scraps);
        materialsList.add(Material.Chaotic_Brimstone);
        materialsList.add(Material.Demonhide);
        database.put(Hotspot.Cultist_Footlocker,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Cadmium_Red);
        materialsList.add(Material.Eye_Of_Dagon);
        materialsList.add(Material.Hellfire_Metal);
        database.put(Hotspot.Sacrificial_Altar,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Keramos);
        materialsList.add(Material.White_marble);
        database.put(Hotspot.Promodroi_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Chaotic_Brimstone);
        materialsList.add(Material.Eye_Of_Dagon);
        materialsList.add(Material.Hellfire_Metal);
        database.put(Hotspot.Dis_Dungeon_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Imperial_Steel);
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Ancient_Vis);
        database.put(Hotspot.Praesidio_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Keramos);
        materialsList.add(Material.Leathe_Scraps);
        materialsList.add(Material.Cobalt_Blue);
        database.put(Hotspot.Monoceros_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.White_Oak);
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Everlight_Silvthril);
        materialsList.add(Material.Star_Of_Saradomin);
        database.put(Hotspot.Amphitheatre_Debris,materialsList);


        materialsList = new ArrayList<>();
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.White_marble);
        database.put(Hotspot.Ceramic_Studio_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Vellum);
        materialsList.add(Material.Ancient_Vis);
        materialsList.add(Material.Blood_Of_Orcus);
        database.put(Hotspot.Carcerem_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Samite_Silk);
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Keramos);
        materialsList.add(Material.Star_Of_Saradomin);
        database.put(Hotspot.Stadio_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Samite_Silk);
        materialsList.add(Material.White_Oak);
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Cadmium_Red);


        materialsList = new ArrayList<>();
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Leathe_Scraps);
        materialsList.add(Material.Chaotic_Brimstone);
        materialsList.add(Material.Hellfire_Metal);
        database.put(Hotspot.Shaktroth_Remains,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Orthenglass);
        materialsList.add(Material.Everlight_Silvthril);
        materialsList.add(Material.Star_Of_Saradomin);
        database.put(Hotspot.Dominion_Games_Podium,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.White_Oak);
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Stormguard_Steel);
        materialsList.add(Material.Wings_Of_War);
        database.put(Hotspot.Ikovian_Memorial,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.Keramos);
        materialsList.add(Material.White_marble);
        materialsList.add(Material.Cobalt_Blue);
        materialsList.add(Material.Everlight_Silvthril);
        database.put(Hotspot.Oikos_Studio_Debris,materialsList);

        materialsList = new ArrayList<>();
        materialsList.add(Material.White_Oak);
        materialsList.add(Material.Third_Age_Iron);
        materialsList.add(Material.Goldrune);
        materialsList.add(Material.Tyrian_Purple);
        database.put(Hotspot.Kharidet_Chapel_Debris,materialsList);

        // TODO: finish the 76+ hotspots

        return database;
    }

    private static ArrayList<Hotspot> FindHotspot(Map<Hotspot, ArrayList<Material>> database, ArrayList<Material> materialSearchQuery) {
       System.out.println("Searching for hotspots containing: " + materialSearchQuery);
        ArrayList <Hotspot> matchedHotspots = new ArrayList<>();
        for(Hotspot hotspot: Hotspot.values()){
            if(database.get(hotspot) != null && database.get(hotspot).containsAll(materialSearchQuery)){
                matchedHotspots.add(hotspot);
            }
        }
        return matchedHotspots;
    }

    public static void main(String[] args) {
        
        Map<Hotspot, ArrayList<Material>> database = PopulateDatabase();
        ArrayList <Material> materialList = new ArrayList<>();
        materialList.add(Material.Zarosian_Insignia);
        materialList.add(Material.Samite_Silk);
        System.out.println(FindHotspot(database,materialList));
    }




}
