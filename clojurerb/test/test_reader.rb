require 'test/helper'

class TestReader < MiniTest::Unit::TestCase
  def setup
    @reader = ClojureRB::Reader.new
  end
  
  def test_integer
    t = @reader.parse("123")
    assert_equal({:integer => "123"}, t)
  end

  def test_nil
    t = @reader.parse("nil")
    assert_equal({:nil => "nil"}, t)
  end
end
