class BingoNumber
    attr_accessor :number
    attr_accessor :marked

    def initialize(number)
        @number = number
        @marked = false
    end

    def mark(value)
        @marked = true if @number == value
    end
end
