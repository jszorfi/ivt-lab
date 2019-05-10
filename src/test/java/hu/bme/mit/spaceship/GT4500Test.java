package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockFirst;
  private TorpedoStore mockSecond;

  @BeforeEach
  public void init(){
    mockFirst = mock(TorpedoStore.class);
    mockSecond = mock(TorpedoStore.class);
    this.ship = new GT4500();
    this.ship.setTorpedoStore(mockFirst, mockSecond);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockFirst.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockFirst).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockFirst.fire(1)).thenReturn(true));
    when(mockSecond.fire(1)).thenReturn(true));
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    verify(mockFirst).fire(1);
    verify(mockSecond).fire(1);
    // Assert
    assertEquals(true, result);
  }

}
