// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
  private static final class Config {
    // placeholders
    public static final int LEFT_MOTOR_SLAVE = 0;
    public static final int RIGHT_MOTOR_SLAVE = 0;
    public static final int LEFT_MOTOR_MASTER = 1;
    public static final int RIGHT_MOTOR_MASTER = 1;
  }

  private CANSparkMax m_leftMotorSlave = new CANSparkMax(Config.LEFT_MOTOR_SLAVE, MotorType.kBrushless);
  private CANSparkMax m_rightMotorSlave = new CANSparkMax(Config.RIGHT_MOTOR_SLAVE, MotorType.kBrushless);
  private CANSparkMax m_leftMotorMaster = new CANSparkMax(Config.LEFT_MOTOR_MASTER, MotorType.kBrushless);
  private CANSparkMax m_rightMotorMaster = new CANSparkMax(Config.RIGHT_MOTOR_MASTER, MotorType.kBrushless);

  private CANEncoder m_encRight = m_rightMotorMaster.getEncoder();
  private CANEncoder m_encLeft = m_leftMotorMaster.getEncoder();

  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorMaster, m_rightMotorMaster);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Inverts masters
    m_leftMotorMaster.setInverted(true);
    m_rightMotorMaster.setInverted(true);

    // Sets following
    m_leftMotorSlave.follow(m_leftMotorMaster);
    m_rightMotorSlave.follow(m_rightMotorMaster);

    m_encLeft.setPositionConversionFactor(1.0);
    m_encRight.setPositionConversionFactor(1.0);
  }

  public void stopMotors() {
    m_leftMotorMaster.stopMotor();
    m_rightMotorMaster.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public DifferentialDrive getDrive() {

    return m_drive;

  }
}
