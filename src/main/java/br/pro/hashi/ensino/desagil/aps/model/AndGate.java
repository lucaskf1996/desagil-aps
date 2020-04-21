package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand;
    private final NandGate nand2;

    public AndGate() {
        super("AND", 2);
        nand = new NandGate();
        nand2 = new NandGate();
        nand2.connect(0, nand);
        nand2.connect(1, nand);
    }

    @Override
    public boolean read() {
        return nand2.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand.connect(inputIndex, emitter);
    }
}
