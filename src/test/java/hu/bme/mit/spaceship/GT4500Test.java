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
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    verify(mockFirst).fire(1);
    verify(mockSecond).fire(1);
    // Assert
    assertEquals(true, result);
  }



  @Test
  public void fire_empty()
  {
    when(mockFirst.isEmpty()).thenReturn(true);
    when(mockSecond.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();

      assertEquals(false, result);
  }

  @Test
  public void fire_emptyAll()
  {
    when(mockFirst.isEmpty()).thenReturn(true);
    when(mockSecond.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

      assertEquals(false, result);
  }

  @Test
  public void fire_failureAll()
  {
    when(mockFirst.fire(1)).thenReturn(false);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

      assertEquals(false, result);
  }

  public void fire_Second()
  {
    ship.setFireOrder(true);
    when(mockSecond.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockFirst, times(0)).fire(1);
    verify(mockSecond, times(1)).fire(1);

      assertEquals(true, result);
  }

  public void fire_First()
  {
    ship.setFireOrder(false);
    when(mockFirst.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockFirst, times(1)).fire(1);
    verify(mockSecond, times(0)).fire(1);

      assertEquals(true, result);
  }

}
