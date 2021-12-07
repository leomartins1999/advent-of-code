# Solution for day 7 of Advent of Code 2021
class Day7
  def solve_first(input)
    positions = read_input(input)

    median = calculate_median(positions)

    calculate_fuel_cost(positions, median)
  end

  def solve_second(input)
    positions = read_input(input)

    median = calculate_median(positions)

    optimal_fuel_cost(positions, median)
  end

  private

  def read_input(input)
    input.split(',').map(&:to_i).sort
  end

  def calculate_median(values)
    l = values.size

    if l % 2 == 0
      (values[l/2 - 1] + values[l/2]) / 2
    else
      values[l/2]
    end
  end

  def calculate_fuel_cost(ship_positions, sync_position, simple = true)
    ship_positions.map { |p| p - sync_position }
      .map(&:abs)
      .map { |v| simple ? v : (1..v).to_a.sum }
      .sum
  end

  def optimal_fuel_cost(ship_positions, sync_position)
    median_cost = calculate_fuel_cost(ship_positions, sync_position, false)
    left_cost = calculate_fuel_cost(ship_positions, sync_position - 1, false)
    right_cost = calculate_fuel_cost(ship_positions, sync_position + 1, false)

    increment = (left_cost < right_cost) ? -1 : 1

    curr_position = sync_position + increment
    curr_cost = [left_cost, right_cost].min
    while true do
      new_cost = calculate_fuel_cost(ship_positions, curr_position + increment, false)

      break if new_cost > curr_cost

      curr_position += increment
      curr_cost = new_cost
    end

    curr_cost
  end
end
