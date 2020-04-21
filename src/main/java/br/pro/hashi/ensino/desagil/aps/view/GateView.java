package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


public class GateView extends FixedPanel implements ActionListener, MouseListener {

    private final Gate gate;
    private final JCheckBox entradaField;
    private final JCheckBox entradaField2;
    private final Switch switch1;
    private final Switch switch2;
    private final Image image;
    private final Light light1;
    private Color color;


    public GateView(Gate gate) {
        super(245, 346);
        this.gate = gate;
        JCheckBox saidaField;

        // Nada de especial na construção dos campos.
        entradaField = new JCheckBox();
        entradaField2 = new JCheckBox();
        saidaField = new JCheckBox();
        switch1 = new Switch();
        switch2 = new Switch();
        light1 = new Light(255, 0, 0);
        light1.connect(0, gate);
        String nome;


        //JLabel entradaLabel = new JLabel("Entrada");
        //JLabel entrada2Label = new JLabel("Entrada2");
        //JLabel saidaLabel = new JLabel("Saida");


        nome = gate.toString();
        if (nome != "NOT") {
            add(entradaField, 20, 150, 25, 25);
            add(entradaField2, 20, 180, 25, 25);
        } else {
            add(entradaField, 10, 175, 25, 25);
        }
        //add(saidaField, 200, 160, 25, 25);


        String name = gate.toString() + ".jpeg";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        entradaField.addActionListener(this);
        entradaField2.addActionListener(this);
        saidaField.addActionListener(this);

        saidaField.setEnabled(false);
        addMouseListener(this);

        gate.connect(0, switch1);
        gate.connect(1, switch2);

        update();
    }

    private void update() {
        color = light1.getColor();
        repaint();


//        result = gate.read();
//        //System.out.println(result);
//        if (result == true){
//            saidaField.setSelected(true);
//        }
//        else if (result == false){
//            saidaField.setSelected(false);
//        }
//        System.out.println(light1.getColor());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        JCheckBox checkbox = (JCheckBox) actionEvent.getSource();
        if (checkbox == entradaField) {
            if (selected) {
                switch1.turnOn();
            } else if (!selected) {
                switch1.turnOff();
            }
        }
        if (checkbox == entradaField2) {
            if (selected) {
                switch2.turnOn();
            } else if (!selected) {
                switch2.turnOff();
            }
        }
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if ((((x - 193) * (x - 193)) + ((y - 185) * (y - 185))) < 140) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);
            light1.setColor(color);
            repaint();

            // ...e chamamos repaint para atualizar a tela.
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 80, 221, 221, this);

        // Desenha um quadrado cheio.

        g.setColor(color);
        g.fillOval(180, 172, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
