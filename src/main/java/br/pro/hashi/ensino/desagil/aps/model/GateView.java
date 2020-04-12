package br.pro.hashi.ensino.desagil.aps.model;

import br.pro.hashi.ensino.desagil.aps.model.Gate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GateView extends JPanel implements ActionListener {

    private final Gate gate;
    private final JCheckBox entradaField;
    private final JCheckBox entradaField2;
    private final JCheckBox saidaField;
    private final Switch switch1;
    private final Switch switch2;





    public GateView(Gate gate) {
        this.gate = gate;

        // Nada de especial na construção dos campos.
        entradaField = new JCheckBox();
        entradaField2 = new JCheckBox();
        saidaField = new JCheckBox();
        switch1 = new Switch();
        switch2 = new Switch();



        JLabel entradaLabel = new JLabel("Entrada");
        JLabel entrada2Label = new JLabel("Entrada2");
        JLabel saidaLabel = new JLabel("Saida");



        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(entradaLabel);
        add(entradaField);
        if (gate.getInputSize() > 1) {
            add(entradaField2);
        }
        add(saidaLabel);
        add(saidaField);



        entradaField.addActionListener(this);
        entradaField2.addActionListener(this);
        saidaField.addActionListener(this);

        saidaField.setEnabled(false);
        update();
    }
    private void update() {
        boolean result;


        result = gate.read();
        //System.out.println(result);
        if (result == true){
            saidaField.setSelected(true);
        }
        else if (result == false){
            saidaField.setSelected(false);
        }

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        JCheckBox checkbox = (JCheckBox) actionEvent.getSource();
        if (checkbox == entradaField) {
            System.out.println("Checkbox1");
            System.out.println(selected);
            if(selected == true){
                switch1.turnOn();
                gate.connect(0, switch1);
            }
            else if (selected == false){
                switch1.turnOff();
                gate.connect(0, switch1);
            }
        }if (checkbox == entradaField2) {
            if(selected == true){
                switch2.turnOn();
                gate.connect(1, switch2);
            }
            else if (selected == false){
                switch2.turnOff();
                gate.connect(1, switch2);
            }
            System.out.println("Checkbox2");
            System.out.println(selected);
        }
        update();

    }
}
