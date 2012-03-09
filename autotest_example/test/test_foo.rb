require 'foo'

class TestFoo < MiniTest::Unit::TestCase
  def test_bar
    f = Foo.new
    assert_equal 'bar', f.bar
  end
end
