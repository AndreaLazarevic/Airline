/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.flights;

import controller.Controller;
import domain.Airplane;
import domain.Airport;
import domain.Flight;
import domain.Reservation;
import forms.ClientMainForm;
import forms.FrmMode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Response;
import validator.FormValidator;

/**
 *
 * @author User
 */
public class FrmFlight extends javax.swing.JDialog {
    ClientMainForm cmf = Controller.getInstance().getClientMainForm();
    SearchFlightsPanel sfp;
    Flight f;
    /**
     * Creates new form FrmFlight
     */
    public FrmFlight(java.awt.Frame parent, boolean modal, FrmMode mode) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        adjustForm(mode);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFlight = new javax.swing.JPanel();
        departureTime = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtFlightID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtPoints = new javax.swing.JTextField();
        cmbAirplanes = new javax.swing.JComboBox<>();
        cmbAirportDeparture = new javax.swing.JComboBox<>();
        cmbAirportArrival = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtTakenSeats = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFlight.setBorder(javax.swing.BorderFactory.createTitledBorder("Flight"));

        jLabel1.setText("Departure time:");

        lblID.setText("Flight ID:");

        jLabel3.setText("Status:");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "On time", "Delayed", "Canceled" }));

        jLabel4.setText("Price (€):");

        jLabel5.setText("Points:");

        jLabel6.setText("Airplane:");

        jLabel7.setText("Airport departure:");

        jLabel8.setText("Airport arrival:");

        cmbAirplanes.setModel(new javax.swing.DefaultComboBoxModel<>(new Airplane[100] ));

        cmbAirportDeparture.setModel(new javax.swing.DefaultComboBoxModel(new Airport[100]));

        cmbAirportArrival.setModel(new javax.swing.DefaultComboBoxModel(new Airport[100]));

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel9.setText("Taken seats:");

        javax.swing.GroupLayout panelFlightLayout = new javax.swing.GroupLayout(panelFlight);
        panelFlight.setLayout(panelFlightLayout);
        panelFlightLayout.setHorizontalGroup(
            panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFlightLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(326, 326, 326))
                    .addGroup(panelFlightLayout.createSequentialGroup()
                        .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFlightLayout.createSequentialGroup()
                                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(51, 51, 51)
                                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbAirportDeparture, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbAirportArrival, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbAirplanes, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFlightLayout.createSequentialGroup()
                                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblID)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(departureTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFlightID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelFlightLayout.createSequentialGroup()
                                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9))
                                .addGap(106, 106, 106)
                                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFlightLayout.createSequentialGroup()
                                        .addComponent(btnUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancel))
                                    .addGroup(panelFlightLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTakenSeats, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtPoints))))))
                        .addGap(18, 18, 18))))
        );
        panelFlightLayout.setVerticalGroup(
            panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFlightLayout.createSequentialGroup()
                        .addComponent(txtFlightID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(departureTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(panelFlightLayout.createSequentialGroup()
                        .addComponent(lblID)
                        .addGap(34, 34, 34)))
                .addGap(6, 6, 6)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTakenSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbAirplanes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAirportDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbAirportArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panelFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Flight f = validation();
        if (f == null) {
            return;
        }

        Response response = Controller.getInstance().saveFlight(f);

        if (response.isSuccessful()) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Successful", JOptionPane.INFORMATION_MESSAGE);
            resetForm();

            cmf.setPanel(new SearchFlightsPanel(cmf));
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        f = validation();
        if (f == null) {
            return;
        }

        f.setFlightID(Integer.parseInt(txtFlightID.getText()));

        Response response = Controller.getInstance().updateFlight(f);
        
        if (response.isSuccessful()) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Successful", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
            sfp.populateFlightsTable();
            sfp.setVisible(true);
            resetForm();
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Airplane> cmbAirplanes;
    private javax.swing.JComboBox<Airport> cmbAirportArrival;
    private javax.swing.JComboBox<Airport> cmbAirportDeparture;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.github.lgooddatepicker.components.DateTimePicker departureTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblID;
    private javax.swing.JPanel panelFlight;
    private javax.swing.JTextField txtFlightID;
    private javax.swing.JTextField txtPoints;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtTakenSeats;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        //txtDate.setText(sdf.format(new Date()));
        txtPrice.setText("");
        txtPoints.setText("");
    }
    
    private void adjustForm(FrmMode mode) {
        populateCmb();

        switch (mode) {
            case NEW:
                lblID.setVisible(false);
                txtFlightID.setVisible(false);
                txtTakenSeats.setText("0");
                txtTakenSeats.setEnabled(false);

                btnSave.setVisible(true);
                btnUpdate.setVisible(false);
                btnCancel.setVisible(true);
                
                setTitle("Add flight:");
                break;
            case EDIT:
                txtFlightID.setEnabled(false);
                txtTakenSeats.setEnabled(false);

                btnSave.setVisible(false);
                btnUpdate.setVisible(true);
                btnCancel.setVisible(true);

                setTitle("Edit flight:");
                break;
            case VIEW:
                lblID.setVisible(true);
                txtFlightID.setVisible(true);

                txtFlightID.setEnabled(false);
                departureTime.setEnabled(false);
                cmbStatus.setEnabled(false);
                txtPrice.setEnabled(false);
                txtPoints.setEnabled(false);
                txtTakenSeats.setEnabled(false);
                cmbAirplanes.setEnabled(false);
                cmbAirportArrival.setEnabled(false);
                cmbAirportDeparture.setEnabled(false);

                btnSave.setVisible(false);
                btnUpdate.setVisible(false);
                btnCancel.setVisible(true);

                setTitle("View flight:");
                break;
        }
    }
    
    // for EDIT and VIEW mode
    void populateForm(Flight f, SearchFlightsPanel sfp, FrmMode mode) {
        adjustForm(mode);
        this.f = f;
        this.sfp = sfp;
        txtFlightID.setText(f.getFlightID().toString());
        departureTime.setDateTimePermissive(f.getDepartureTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        cmbStatus.setSelectedItem(f.getStatus());
        txtPrice.setText(f.getPrice().toString());
        txtPoints.setText(f.getPoints()+"");
        txtTakenSeats.setText(f.getTakenSeats().toString());
        cmbAirplanes.setSelectedItem(f.getAirplane());
        cmbAirportDeparture.setSelectedItem(f.getAirportDeparture());
        cmbAirportArrival.setSelectedItem(f.getAirportArrival());
    }

    private void populateCmb() {
        populateCmbAirplanes();
        populateCmbAirports();
    }

    private void populateCmbAirplanes() {
        cmbAirplanes.removeAllItems();
        List<Airplane> airplanes = Controller.getInstance().getAllAirplanes();
        for (Airplane airplane : airplanes) {
            cmbAirplanes.addItem(airplane);
        }
        
    }

    private void populateCmbAirports() {
        cmbAirportArrival.removeAllItems();
        cmbAirportDeparture.removeAllItems();
        
        List<Airport> airports = Controller.getInstance().getAllAirports();
        for (Airport airport : airports) {
            cmbAirportArrival.addItem(airport);
            cmbAirportDeparture.addItem(airport);
        }
    }

    private Flight validation() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateFinal;
        String date = "";
        String time = "";
        
        try{
            date = departureTime.getDateTimePermissive().toString().split("T")[0];
            time = departureTime.getDateTimePermissive().toString().split("T")[1];
        } catch(NullPointerException ex){
            JOptionPane.showMessageDialog(this, "You haven't entered the date!");
            return null;
        }
        
        String dateTimeFormatted = date + " " + time; 

    
        try {
            dateFinal = sdf.parse(dateTimeFormatted);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Date of the projection has to be in right format!");
            return null;
        }
        
        java.sql.Date dateDB = new java.sql.Date(dateFinal.getTime());
        
        if (dateFinal.before(new Date())) {
            JOptionPane.showMessageDialog(null, "You need to choose a date after the current date.");
            return null;
        }
        
        boolean valid = FormValidator.getInstance().validateEmpty(txtPrice, txtPoints);
        if (!valid) {
            JOptionPane.showMessageDialog(this, FormValidator.getInstance().getValidationMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        String status = cmbStatus.getSelectedItem().toString();
        
        double price;
        int points;
        int takenSeats;
        
        try {
            price = Double.parseDouble(txtPrice.getText());
            points = Integer.parseInt(txtPoints.getText());
            takenSeats = Integer.parseInt(txtTakenSeats.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Price and points need to be numeric values!");
            return null;
        }

        Airplane airplane = new Airplane();
        Airport airportDeparture = new Airport();
        Airport airportArrival = new Airport();
        
        airplane = (Airplane) cmbAirplanes.getSelectedItem();
        airportDeparture = (Airport) cmbAirportDeparture.getSelectedItem();
        airportArrival = (Airport) cmbAirportArrival.getSelectedItem();
        
        if (airportArrival.equals(airportDeparture)) {
            JOptionPane.showMessageDialog(null, "Airport arrival must differ from airport departure.");
            return null;
        }
        
        Flight f = new Flight(-1, dateDB, status, price, points, takenSeats, airplane, airportDeparture, airportArrival);
        return f;
    }
}
