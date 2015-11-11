package com.inc.haitran.five_elements.dbview;

/**
 * Created by apple on 9/9/15.
 */
public class EnumDB {
    public static final int none = -1;
    //
    public static final int element_kind_metal = 0;
    public static final int element_kind_water = 1;
    public static final int element_kind_wood = 2;
    public static final int element_kind_fire = 3;
    public static final int element_kind_earth = 4;
    public  enum EElementKind
    {
        none(-1),metal(element_kind_metal),water(element_kind_water),wood(element_kind_wood),
        fire(element_kind_fire),earth(element_kind_earth);
        private final int value;
        private EElementKind(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public static EElementKind fromInteger(int x) {
            switch(x) {
                case  EnumDB.element_kind_metal:
                    return metal;
                case EnumDB.element_kind_water:
                    return water;
                case EnumDB.element_kind_wood:
                    return wood;
                case EnumDB.element_kind_fire:
                    return fire;
                case EnumDB.element_kind_earth:
                    return earth;
            }
            return none;
        }
    }
    //Equip Kind

    public static final int equip_kind_weapons = 0;
    public static final int equip_kind_hat = 1;
    public static final int equip_kind_glove = 2;//bao tay
    public static final int equip_kind_shirt = 3;
    public static final int equip_kind_belt = 4;//đai lưng
    public static final int equip_kind_shoes = 5;
    public static final int equip_kind_rings_down = 6;
    public static final int equip_kind_ring_up = 7;
    public static final int equip_kind_pearl = 8;
    public static final int equip_kind_chain = 9;
    public  enum EEquipmentKind
    {

        none(-1),weapons(equip_kind_weapons),hat(equip_kind_hat),glove(equip_kind_glove),shirt(equip_kind_shirt),belt(equip_kind_belt),
        shoes(equip_kind_shoes),rings_down(equip_kind_rings_down),ring_up(equip_kind_ring_up),pearl(equip_kind_pearl),chain(equip_kind_chain);
        private final int value;
        private EEquipmentKind(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public static EEquipmentKind fromInteger(int x) {
            switch(x) {
                case EnumDB.equip_kind_weapons:
                    return weapons;
                case EnumDB.equip_kind_hat:
                    return hat;
                case EnumDB.equip_kind_glove:
                    return glove;
                case EnumDB.equip_kind_shirt:
                    return shirt;
                case EnumDB.equip_kind_belt:
                    return belt;
                case EnumDB.equip_kind_shoes:
                    return shoes;
                case EnumDB.equip_kind_rings_down:
                    return rings_down;
                case EnumDB.equip_kind_ring_up:
                    return ring_up;
                case EnumDB.equip_kind_pearl:
                    return pearl;
                case EnumDB.equip_kind_chain:
                    return chain;
            }
            return none;
        }
    }
    //
    public static final int equip_type_white = 0;
    public static final int equip_type_blue = 1;
    public static final int equip_type_gold = 2;
    public  enum EEQuipType {
        none(-1), white(equip_type_white), blue(equip_type_blue), gold(equip_type_gold);
        private final int value;

        private EEQuipType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EEQuipType fromInteger(int x) {
            switch (x) {
                case EnumDB.equip_type_white:
                    return white;
                case EnumDB.equip_type_blue:
                    return blue;
                case EnumDB.equip_type_gold:
                    return gold;
            }
            return none;
        }
    }
}
