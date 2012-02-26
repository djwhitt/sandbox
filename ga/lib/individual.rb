class Individual
  DEFAULT_GENE_LENGTH = 64

  attr_accessor :genes, :length

  def initialize(length=DEFAULT_GENE_LENGTH)
    @genes = []
    @length = DEFAULT_GENE_LENGTH
  end

  def generate
    @length.times do |i|
      @genes[i] = rand(2)
    end
  end
end
