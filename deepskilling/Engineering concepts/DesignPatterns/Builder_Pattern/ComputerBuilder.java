public class ComputerBuilder {
    private String CPU;
    private String RAM;
    
    public static class Builder {
        private String CPU;
        private String RAM;
        public Builder setCPU(String c) { this.CPU = c; return this; }
        public Builder setRAM(String r) { this.RAM = r; return this; }
        public ComputerBuilder build() { return new ComputerBuilder(this); }
    }
    
    private ComputerBuilder(Builder b) {
        this.CPU = b.CPU;
        this.RAM = b.RAM;
    }
}
