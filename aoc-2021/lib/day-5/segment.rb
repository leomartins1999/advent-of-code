class Segment
    attr_reader :start
    attr_reader :end

    def initialize(start, _end)
        @start = start
        @end = _end
    end

    def valid?
        horizontal? || vertical? || diagonal?
    end

    def horizontal?
        @start.y == @end.y
    end

    def vertical?
        @start.x == @end.x
    end

    def diagonal?
        min_x, max_x = [@start.x, @end.x].minmax
        min_y, max_y = [@start.y, @end.y].minmax

        delta_x = max_x - min_x
        delta_y = max_y - min_y

        delta_x == delta_y
    end
end
