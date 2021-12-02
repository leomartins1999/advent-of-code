# Representation of Point
class Point
  
  def initialize(x, y, aim)
    @x = x
    @y = y
    @aim = aim
  end

  def add_movement(movement)
    direction, value = movement

    case direction
    when 'forward'
      Point.new(@x + value, @y, 0)
    when 'up'
      Point.new(@x, @y + value, 0)
    when 'down'
      Point.new(@x, @y - value, 0)
    else clone
    end
  end

  def add_movement_with_aim(movement)
    direction, value = movement

    case direction
    when 'forward'
      Point.new(@x + value, @y + @aim * value, @aim)
    when 'up'
      Point.new(@x, @y, @aim - value)
    when 'down'
      Point.new(@x, @y, @aim + value)
    else clone
    end
  end

  def calculate_distance_traveled
    @x.abs * @y.abs
  end
end
