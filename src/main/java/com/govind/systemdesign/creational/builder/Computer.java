package com.govind.systemdesign.creational.builder;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class Computer {
    private String name;
    private String software;
    private String ram;
    private String hdd;
    private boolean hasGraphicCard;
    private boolean hasBluetoothEnabled;

    public Computer(ComputerBuilder computerBuilder) {
        name = computerBuilder.name;
        software = computerBuilder.software;
        ram = computerBuilder.ram;
        hdd = computerBuilder.hdd;
        hasBluetoothEnabled = computerBuilder.hasBluetoothEnabled;
        hasGraphicCard = computerBuilder.hasGraphicCard;
    }

    public static class ComputerBuilder{
        private String name;
        private String software;
        private String ram;
        private String hdd;
        private boolean hasGraphicCard;
        private boolean hasBluetoothEnabled;

        public ComputerBuilder(String name, String software){
            this.name = name;
            this.software = software;
            this.ram = "2G";
            this.hdd = "500G";
        }

        public ComputerBuilder setHasGraphicCard(boolean graphicCard){
            this.hasGraphicCard = graphicCard;
            return this;
        }

        public ComputerBuilder setHashBluetoothEnabled(boolean bluetoothEnabled){
            this.hasBluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", software='" + software + '\'' +
                ", ram='" + ram + '\'' +
                ", hdd='" + hdd + '\'' +
                ", hasGraphicCard=" + hasGraphicCard +
                ", hasBluetoothEnabled=" + hasBluetoothEnabled +
                '}';
    }
}
