class BingoGame
  attr_accessor :numbers, :boards

  def initialize(numbers, boards)
    @numbers = numbers
    @boards = boards
  end

  def play
    last_value = @numbers.first

    @numbers.each do |nr|
        break if game_over?

        last_value = nr

        mark_boards(nr)
    end

    [get_winner, last_value]
  end

  def get_loser

    while @boards.size > 0 && @numbers.size > 0 do
      winner, last_value = play
      
      update_board(winner)
      update_numbers(last_value)
    end

    [winner, last_value]
  end

  private

  def mark_boards(nr)
    @boards.each { |board| board.mark_number(nr) }
  end

  def game_over?
    @boards.any?(&:won?)
  end

  def get_winner
    @boards.find(&:won?)
  end

  def update_board(winner)
    @boards.delete(winner)
  end

  def update_numbers(last_value)
    idx = @numbers.index(last_value)

    @numbers = @numbers[idx .. @numbers.size - 1]
  end
end
