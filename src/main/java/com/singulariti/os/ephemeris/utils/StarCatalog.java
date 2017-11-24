/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singulariti.os.ephemeris.utils;

import com.singulariti.os.ephemeris.domain.Star;
import static com.singulariti.os.ephemeris.utils.CollectionUtils.singletonCollector;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author John
 */
public class StarCatalog {

    private static final List<Star> STARS;

    static {
        STARS = new ArrayList<Star>() {
            {
                add(new Star("a", "AND", "Alpheratz", "00:08:23", "+29:05:00", "207", "SD", "B8"));
                add(new Star("b", "AND", "Mirach", "01:09:44", "+35:37:00", "206", "SD", "M0"));
                add(new Star("a", "AQL", "Altair", "19:50:47", "+08:52:00", "075", "SD", "A7"));
                add(new Star("g", "AQL", "Tarazed", "19:46:15", "+10:37:00", "272", "SS", "K3"));
                add(new Star("a", "AQR", "Sadalmelik", "22:05:47", "-00:19:00", "293", "SS", "G2"));
                add(new Star("b", "AQR", "Sadalsud", "21:31:34", "-05:35:00", "288", "SD", "G0"));
                add(new Star("d", "AQR", "Skat", "22:54:39", "-15:49:00", "327", "SS", "A3"));
                add(new Star("g", "AQR", "Sadachbia", "22:21:39", "-01:23:00", "384", "SD", "B9"));
                add(new Star("a", "ARI", "Hamal", "02:07:10", "+23:27:00", "201", "SS", "K2"));
                add(new Star("b", "ARI", "Sheratan", "01:54:39", "+20:48:00", "263", "SS", "A5"));
                add(new Star("a", "AUR", "Capella", "05:17:32", "+46:00:31", "008", "SS", "G6/G2"));
                add(new Star("b", "AUR", "Menkalinan", "05:59:32", "+44:36:51", "190", "SD", "A2"));
                add(new Star("a", "BOO", "Arcturus", "14:15:40", "+19:11:00", "-11", "SS", "K2"));
                add(new Star("b", "BOO", "Nekkar", "15:01:57", "+40:23:00", "349", "SS", "G8"));
                add(new Star("e", "BOO", "Pulcherrima", "14:44:59", "+27:05:00", "240", "SD", "K0"));
                add(new Star("h", "BOO", "Muphrid", "13:54:41", "+18:24:00", "268", "SS", "G0"));
                add(new Star("d", "CAP", "Deneb Algiedi", "21:47:02", "-16:08:00", "283", "SD", "A"));
                add(new Star("a", "CAR", "Canopus", "06:23:57", "-52:41:00", "-72", "SS", "F0"));
                add(new Star("a", "CAS", "Schedar", "00:40:31", "+56:32:00", "222", "SD", "K0"));
                add(new Star("b", "CAS", "Caph", "00:09:10", "+59:09:00", "227", "SD", "F2"));
                add(new Star("a", "CEN", "Rigil Kentaurus", "14:39:36", "-60:50:00", "033", "SD", "G2"));
                add(new Star("b", "CEN", "Hadar (Agena)", "14:03:50", "-60:22:00", "061", "SD", "B1"));
                add(new Star("a", "CEP", "Alderamin", "21:18:35", "+62:35:00", "244", "SD", "A7"));
                add(new Star("b", "CEP", "Alphirk", "21:28:39", "+70:33:00", "318", "SD", "B2"));
                add(new Star("g", "CEP", "Alrai", "23:39:20", "+77:37:00", "322", "SS", "K1"));
                add(new Star("a", "CET", "Mekab", "03:02:17", "+04:06:00", "252", "SS", "M2"));
                add(new Star("b", "CET", "Diphda", "00:43:35", "-17:59:00", "203", "SS", "K1"));
                add(new Star("o", "CET", "Mira", "02:19:21", "-02:59:00", "200", "SD", "M6"));
                add(new Star("z", "CET", "Baten", "01:51:27", "-10:20:00", "372", "SS", "K2"));
                add(new Star("a", "CMA", "Sirius", "06:45:09", "-16:43:00", "-146", "SD", "A1"));
                add(new Star("b", "CMA", "Mirzam", "06:22:42", "-17:57:00", "198", "SS", "B1"));
                add(new Star("e", "CMA", "Adara", "06:58:38", "-28:58:00", "150", "SD", "B2"));
                add(new Star("a", "CMI", "Procyon", "07:39:18", "+05:14:00", "036", "SD", "F5"));
                add(new Star("b", "CMI", "Gomeisa", "07:27:09", "+08:17:00", "289", "SS", "B7"));
                add(new Star("a", "COL", "Phakt", "05:39:39", "-34:05:00", "263", "SD", "B8"));
                add(new Star("a", "CRA", "Alkes", "19:09:28", "-37:55:00", "410", "SS", "A2"));
                add(new Star("a", "CRB", "Alphecca", "15:34:41", "+26:43:00", "223", "SS", "A0"));
                add(new Star("a1", "CRU", "Acrux", "12:26:36", "-63:06:00", "080", "SD", "B2"));
                add(new Star("b", "CRU", "Mimosa", "12:47:44", "-59:42:00", "127", "SD", "B0"));
                add(new Star("a2", "CVN", "Cor Caroli", "12:56:02", "+38:19:00", "288", "SD", "B9"));
                add(new Star("a", "CYG", "Deneb", "20:41:26", "+45:16:00", "125", "SD", "A2"));
                add(new Star("b", "CYG", "Albireo", "19:30:43", "+27:58:00", "308", "SD", "K5"));
                add(new Star("b", "CYG", "Albireo", "19:30:45", "+27:58:00", "511", "SD", "B8"));
                add(new Star("a", "DEL", "Svalocin", "20:39:39", "+15:55:00", "377", "SD", "B9"));
                add(new Star("b", "DEL", "Rotanev", "20:37:33", "+14:36:00", "358", "SD", "F5"));
                add(new Star("a", "DRA", "Thuban", "14:04:24", "+64:22:00", "365", "SS", "A0"));
                add(new Star("b", "DRA", "Alwaid", "17:30:26", "+52:19:00", "279", "SD", "G2"));
                add(new Star("g", "DRA", "Rastaban", "17:56:36", "+51:29:00", "223", "SD", "K5"));
                add(new Star("a", "ERI", "Achernar", "01:37:42", "-57:15:00", "049", "SS", "B5"));
                add(new Star("b", "ERI", "Cursa", "05:07:51", "-05:05:00", "277", "SS", "A3"));
                add(new Star("g", "ERI", "Zaurak", "03:58:02", "-13:31:00", "295", "SD", "M0"));
                add(new Star("a", "GEM", "Castor", "07:34:36", "+31:53:00", "199", "SD", "A"));
                add(new Star("b", "GEM", "Pollux", "07:45:19", "+28:01:00", "115", "SD", "K0"));
                add(new Star("d", "GEM", "Wasat", "07:20:07", "+21:59:00", "353", "SD", "F0"));
                add(new Star("e", "GEM", "MEbsuta", "06:43:56", "+25:08:00", "299", "SD", "G8"));
                add(new Star("b", "HER", "Kornephoros", "16:30:13", "+21:29:00", "278", "SS", "G8"));
                add(new Star("k", "HER", "Marsik", "16:08:05", "+17:03:00", "500", "SD", "G8"));
                add(new Star("a", "LEO", "Regulus", "10:08:22", "+11:58:00", "135", "SD", "B7"));
                add(new Star("b", "LEO", "Denebola", "11:49:04", "+14:34:00", "213", "SD", "A3"));
                add(new Star("d", "LEO", "Zozca, Zosma", "11:14:06", "+20:31:00", "256", "SS", "A4"));
                add(new Star("a", "LEP", "Arneb", "05:32:44", "-17:50:00", "258", "SD", "F0"));
                add(new Star("b", "LIB", "Zuben el Chamali", "15:17:00", "-09:23:00", "261", "SS", "B8"));
                add(new Star("g", "LIB", "Zuben el Hakrabi", "15:35:32", "-14:47:00", "390", "SD", "G8"));
                add(new Star("a", "LYR", "Vega", "18:36:56", "+38:47:00", "004", "SD", "A0"));
                add(new Star("b", "LYR", "Sheliak", "18:50:04", "+33:22:00", "343", "SD", "B7"));
                add(new Star("g", "LYR", "Sulaphat", "18:58:56", "+32:41:00", "325", "SD", "B9"));
                add(new Star("a", "OPH", "Ras Alhague", "17:34:56", "+12:34:00", "207", "SS", "A5"));
                add(new Star("b", "OPH", "Kelb al Rai", "17:43:28", "+04:34:00", "277", "SS", "K2"));
                add(new Star("a", "ORI", "Betelgeuse", "05:55:10", "+07:24:00", "080", "SD", "M2"));
                add(new Star("b", "ORI", "Rigel", "05:14:32", "-08:12:00", "015", "SD", "B8"));
                add(new Star("d", "ORI", "Mintaka", "05:32:01", "-00:18:00", "220", "SD", "O9"));
                add(new Star("e", "ORI", "Alnilam", "05:36:12", "-01:12:00", "169", "SS", "B0"));
                add(new Star("g", "ORI", "Bellatrix", "05:25:08", "+06:21:00", "163", "SS", "B2"));
                add(new Star("a", "PEG", "Markab", "23:04:46", "+15:12:00", "250", "SS", "B9"));
                add(new Star("e", "PEG", "Eniph", "21:44:11", "+09:53:00", "240", "SD", "K2"));
                add(new Star("g", "PEG", "Algenib", "00:13:14", "+15:11:00", "283", "SS", "B2"));
                add(new Star("z", "PEG", "Homam", "22:41:27", "+10:50:00", "339", "SD", "B8"));
                add(new Star("a", "PER", "Mirfak", "03:24:20", "+49:51:00", "180", "SS", "F5"));
                add(new Star("b", "PER", "Algol", "03:08:11", "+40:57:00", "215", "SD", "B8"));
                add(new Star("a", "PSA", "Fomalhaut", "22:57:39", "-29:37:00", "115", "SS", "A3"));
                add(new Star("a", "PSC", "Kaitain", "02:02:02", "+02:46:00", "433", "SD", "A"));
                add(new Star("a", "SCO", "Antares", "16:29:25", "-26:26:00", "108", "SD", "M1"));
                add(new Star("l", "SCO", "Shaula", "17:33:36", "-37:06:00", "162", "SS", "B1"));
                add(new Star("a", "SER", "Unukalhay", "15:44:17", "+06:25:00", "265", "SD", "K2"));
                add(new Star("e", "SGR", "Kaus Australis", "18:24:10", "-34:23:00", "183", "SD", "B9"));
                add(new Star("17", "TAU", "Electra", "03:44:52", "+24:07:00", "370", "SS", "B6"));
                add(new Star("19", "TAU", "Taygeta", "03:45:12", "+24:28:00", "430", "SS", "B6"));
                add(new Star("20", "TAU", "Maia", "03:45:49", "+24:22:00", "388", "SS", "B7"));
                add(new Star("21", "TAU", "Asterope", "03:45:55", "+24:34:00", "576", "SS", "B8"));
                add(new Star("23", "TAU", "Merope", "03:46:19", "+23:57:00", "418", "SS", "B6"));
                add(new Star("27", "TAU", "Atlas", "03:49:10", "+24:03:00", "363", "SD", "B8"));
                add(new Star("28", "TAU", "Pleione", "03:49:11", "+24:08:00", "505", "SS", "B8"));
                add(new Star("a", "TAU", "Aldebaran", "04:35:55", "+16:30:00", "086", "SD", "K5"));
                add(new Star("b", "TAU", "Alnath", "05:26:17", "+28:36:00", "165", "SS", "B7"));
                add(new Star("h", "TAU", "Alcyone", "03:47:29", "+24:07:00", "287", "SS", "B7"));
                add(new Star("80", "UMA", "Alcor", "13:25:13", "+55:00:00", "400", "SS", "A5"));
                add(new Star("a", "UMA", "Dubhe", "11:03:44", "+61:45:00", "179", "SD", "K0"));
                add(new Star("b", "UMA", "Merak", "11:01:51", "+56:23:00", "238", "SS", "A1"));
                add(new Star("d", "UMA", "Megrez", "12:15:26", "+57:02:00", "331", "SS", "A3"));
                add(new Star("e", "UMA", "Alioth", "12:54:02", "+55:57:00", "179", "SS", "A0"));
                add(new Star("g", "UMA", "Phecda", "11:53:49", "+53:42:00", "243", "SS", "A0"));
                add(new Star("h", "UMA", "Benetnasch", "13:47:32", "+49:19:00", "188", "SS", "B3"));
                add(new Star("i", "UMA", "Talitha", "08:59:13", "+48:02:00", "315", "SD", "A7"));
                add(new Star("z", "UMA", "Mizar", "13:23:56", "+54:56:00", "227", "SD", "A2"));
                add(new Star("a", "UMI", "Polaris", "02:31:13", "+89:15:00", "204", "SD", "F8"));
                add(new Star("e", "VIR", "Vindemiatrix", "13:02:11", "+10:58:00", "283", "SS", "G9"));
                add(new Star("b", "UMI", "Kocab", "14:50:43", "+74:09:00", "207", "SS", "K4"));
                add(new Star("a", "VIR", "Spica", "13:25:11", "-11:09:00", "097", "SS", "B1"));
                add(new Star("b", "VIR", "Zawijah", "11:50:42", "+01:46:00", "361", "SS", "F8"));
            }
        };
    }

    private StarCatalog() {
    }

    public static List<Star> getStars() {
        return STARS;
    }

    public static Optional<Star> byIdAndConstellation(String id, String constellation) {
        return STARS.stream().filter(star -> (star.getConstellation().equalsIgnoreCase(constellation) && star.getId().equalsIgnoreCase(id))).findAny();
    }

    public static Star byName(String name) {
        return STARS.stream().filter(star -> (star.getTraditionalName().equalsIgnoreCase(name))).collect(singletonCollector());
    }

}
