class OctopusHerd
  def initialize(octopuses)
    @elems = octopuses
    @flash_count = 0

    @x_size = @elems.first.size
    @y_size = @elems.size
  end

  def get_number_of_flashes(steps = 100)
    (1..steps).each { |_| make_step }

    @flash_count
  end

  def get_day_for_all_octopuses
    step = 0

    while true
      make_step
      step += 1

      return step if are_all_octopuses_flashing?
    end
  end

  private

  def make_step
    add_step_to_all

    simulate_flashes
  end

  def add_step_to_all
    @elems.map! { |line| line.map(&:next) }
  end

  def simulate_flashes
    while true
      flashing_octopuses = get_flashing_coordinates
      break if flashing_octopuses.size == 0

      flashing_octopuses.each { |oct_x, oct_y| increment_surrounding_octopuses(oct_x, oct_y) }

      @flash_count += flashing_octopuses.size
    end
  end

  def get_flashing_coordinates
    res = []

    (0..@y_size - 1).each do |y|
      (0..@x_size - 1).each do |x|
        res << [x, y] if @elems[y][x] == 10
      end
    end

    res
  end

  def increment_surrounding_octopuses(oct_x, oct_y)
    surrounding_elements = get_surrounding_elements(oct_x, oct_y)

    surrounding_elements.each do |x, y|
      value = @elems[y][x]
      @elems[y][x] += 1 if value != 0 && value != 10
    end

    @elems[oct_y][oct_x] = 0
  end

  def get_surrounding_elements(x, y)
    max_x = @x_size - 1
    max_y = @y_size - 1

    up = y > 0 ? [x, y - 1] : nil
    right = x < max_x ? [x + 1, y] : nil
    down = y < max_y ? [x, y + 1] : nil
    left = x > 0 ? [x - 1, y] : nil

    top_right = x < max_x && y > 0 ? [x + 1, y - 1] : nil
    down_right = x < max_x && y < max_y ? [x + 1, y + 1] : nil
    down_left = x > 0 && y < max_y ? [x - 1, y + 1] : nil
    top_left = y > 0 && x > 0 ? [x - 1, y - 1] : nil

    [up, top_right, right, down_right, down, down_left, left, top_left].reject(&:nil?)
  end

  def are_all_octopuses_flashing?
    @elems.all? { |line| line.all? { |v| v == 0 } }
  end
end
