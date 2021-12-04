require_relative 'bingo_number'

class BingoBoard
  attr_accessor :numbers

  def initialize(lines)
    @numbers = lines
      .map { |line| line.split(' ') }
      .map { |line| line.map { |nr| BingoNumber.new(nr.to_i) } }

    @line_length = @numbers.first.size
  end

  def mark_number(value)
    @numbers.each do |line|
      line.each { |nr| nr.mark(value) }
    end
  end

  def won? 
    horizontal_win? or vertical_win?
  end

  def get_final_score(last_value)
    score = @numbers
      .map { |line| line.reject { |nr| nr.marked } }
      .map { |line| line.map { |nr| nr.number } }
      .map { |line| line.sum }
      .sum

    score * last_value
  end

  private

  def horizontal_win?
    @numbers.any? { |line| line.all? { |nr| nr.marked } }
  end

  def vertical_win?
    (0..@line_length - 1).any? do |idx|
      @numbers
        .map { |line| line[idx] }
        .all? { |nr| nr.marked }
    end
  end
end
