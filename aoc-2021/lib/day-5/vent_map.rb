DANGER_THRESHOLD = 2.freeze

class VentMap

    def initialize(map_size, diagonal_enabled)
        @map_size = map_size
        @diagonal_enabled = diagonal_enabled

        @map = Array.new(map_size) { Array.new(map_size) { 0 } }
    end

    def add_segment(segment)
        add_horizontal_segment(segment) if segment.horizontal?
        add_vertical_segment(segment) if segment.vertical?
        add_diagonal_segment(segment) if segment.diagonal? && @diagonal_enabled
    end

    def number_of_danger_areas
        @map
            .map { |line| line.reject { |v| v < DANGER_THRESHOLD } .size }
            .sum
    end

    private

    def add_horizontal_segment(segment)
        y = segment.start.y

        start_x, end_x = [segment.start.x, segment.end.x].minmax

        (start_x..end_x).each do |x|
            @map[y][x] += 1
        end
    end

    def add_vertical_segment(segment)
        x = segment.start.x

        start_y, end_y = [segment.start.y, segment.end.y].minmax

        (start_y..end_y).each do |y|
            @map[y][x] += 1
        end
    end

    def add_diagonal_segment(segment)
        x = segment.start.x
        y = segment.start.y

        end_x = segment.end.x
        end_y = segment.end.y

        while true do
            @map[y][x] += 1

            break if x == end_x && y == end_y

            x = next_point(x, end_x)
            y = next_point(y, end_y)
        end
    end

    def next_point(v, end_v)
        if v <= end_v
            v + 1
        else
            v - 1
        end
    end

end
