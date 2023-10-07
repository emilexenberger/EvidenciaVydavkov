package sk.tmconsulting.view;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class GUI {
    private static int indexVydavku;

    public static void main(String[] args) {
        // Frame
        JFrame hlavneOkno = new JFrame("Evidencia výdavkov"); // vytvorime okno
        hlavneOkno.setMinimumSize(new Dimension(800, 500));
        hlavneOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ked kliknem na X na okne (cize vo frame) tak sa zatvori standardne
        hlavneOkno.setLocationRelativeTo(null); // vycentrovanie okna

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null); // layout pre panel bude null, cize prazdny
        hlavneOkno.setContentPane(panel); // my dany panel pridame do frame

        // Label - Nazov vydavku
        JLabel labelNazovVydavku = new JLabel("Názov výdavku", SwingConstants.RIGHT);
        labelNazovVydavku.setBounds(85, 65, 100, 20);
        panel.add(labelNazovVydavku);

        // JText - Nazov vydavku
        JTextField txtNazovVydavku = new JTextField();
        txtNazovVydavku.setBounds(190, 60, 200, 30); // x, y, sirka, vyska
        panel.add(txtNazovVydavku);

        // Label - Cena
        JLabel labelCena = new JLabel("Cena", SwingConstants.RIGHT);
        labelCena.setBounds(85, 95, 100, 20);
        panel.add(labelCena);

        // JText - Cena
        JTextField txtCena = new JTextField();
        txtCena.setBounds(190, 90, 200, 30); // x, y, sirka, vyska
        panel.add(txtCena);

        // Label - Kategoria
        JLabel labelKategoria = new JLabel("Kategória", SwingConstants.RIGHT);
        labelKategoria.setBounds(85, 125, 100, 20);
        panel.add(labelKategoria);

        // Combobox - Kategoria
        String[] KategoriaOptions = {"POTRAVINY", "PHM", "OBLEČENIE", "KONÍČKY", "INÉ"};
        JComboBox<String> cmbKategoria = new JComboBox<>(KategoriaOptions);
        cmbKategoria.setBounds(190, 120, 200, 30);
        panel.add(cmbKategoria);

        // Label - Datum
        JLabel labelDatum = new JLabel("Dátum", SwingConstants.RIGHT);
        labelDatum.setBounds(85, 155, 100, 20);
        panel.add(labelDatum);

        // Date picker
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(190, 150, 200, 30);
        panel.add(datePicker);

        // Label - Zoznam vydavkov
        JLabel lblZoznamVydavkov = new JLabel("Zoznam výdavkov");
        lblZoznamVydavkov.setBounds(420, 35, 150, 20);
        panel.add(lblZoznamVydavkov);

        // List - Zoznam vydavkov
        DefaultListModel modelZoznamu = new DefaultListModel<>();
        JList lstZoznamVydavkov = new JList(modelZoznamu);

        // Pridanie testovacich udajov
        modelZoznamu.addElement("Chlieb 2.3 POTRAVINY 27.09.2023");
        modelZoznamu.addElement("Rožky 0.5 POTRAVINY 27.09.2023");
        modelZoznamu.addElement("Nafta 40.0 PHM 25.09.2023");

        lstZoznamVydavkov.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {

                        String vybranyVydavok = lstZoznamVydavkov.getSelectedValue().toString();
                        String jednotliveUdajeVydavku[] = vybranyVydavok.split(" ");
                        System.out.println(Arrays.toString(jednotliveUdajeVydavku));

                        // Vyplnime datum
                        String vybranyDatum = jednotliveUdajeVydavku[3];
                        String slovakDate[] = vybranyDatum.split("\\.");
                        model.setDate(Integer.parseInt(slovakDate[2]), Integer.parseInt(slovakDate[1]) - 1, Integer.parseInt(slovakDate[0]));
                        model.setSelected(true);

                        // Naplnime jednotlive texfields
                        txtNazovVydavku.setText(jednotliveUdajeVydavku[0]);
                        txtCena.setText(jednotliveUdajeVydavku[1]);
                        cmbKategoria.setSelectedItem(jednotliveUdajeVydavku[2]);

                        indexVydavku = lstZoznamVydavkov.getSelectedIndex();
                    } catch (NullPointerException e1) {
                        // TODO Spracovat
                    }
                }
            }
        });

        // ScrollPane - Zoznam vydavkov
        JScrollPane scpZoznamVydavkov = new JScrollPane(lstZoznamVydavkov);
        scpZoznamVydavkov.setBounds(420, 65, 330, 300);
        panel.add(scpZoznamVydavkov);

        // Button - Pridaj zaznam
        JButton btnPridajZanam = new JButton("Pridaj záznam");
        btnPridajZanam.setBounds(85, 405, 120, 30);

        btnPridajZanam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String date = simpleDateFormat.format(datePicker.getModel().getValue());
                modelZoznamu.addElement(txtNazovVydavku.getText() + " " + txtCena.getText() + " " + cmbKategoria.getSelectedItem() + " " + date);
            }
        });
        panel.add(btnPridajZanam);

        // Button - uprav zaznam
        JButton btnUpravZaznam = new JButton("Uprav záznam");
        btnUpravZaznam.setBounds(225, 405, 120, 30);

        btnUpravZaznam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String date = simpleDateFormat.format(datePicker.getModel().getValue());
                modelZoznamu.set(indexVydavku, txtNazovVydavku.getText() + " " + txtCena.getText() + " " + cmbKategoria.getSelectedItem() + " " + date);
            }
        });
        panel.add(btnUpravZaznam);

        // Button - Vymaz zaznam
        JButton btnVymazZaznam = new JButton("Vymaž záznam");
        btnVymazZaznam.setBounds(365, 405, 130, 30);

        btnVymazZaznam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Po kliknuti na tlacidlo musime doprogramovat ...
                modelZoznamu.remove(indexVydavku);
            }
        });
        panel.add(btnVymazZaznam);

        // Button - Zatvor aplikaciu
        JButton btnZatvorAplikaciu = new JButton("Zatvor aplikáciu");
        btnZatvorAplikaciu.setBounds(515, 405, 130, 30);

        btnZatvorAplikaciu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hlavneOkno.dispatchEvent(new WindowEvent(hlavneOkno, WindowEvent.WINDOW_CLOSING)); // Korektne zatvori celu SWING aplikaciu
            }
        });
        panel.add(btnZatvorAplikaciu);

        // Display
        hlavneOkno.pack();
        hlavneOkno.setVisible(true);
    }
}
