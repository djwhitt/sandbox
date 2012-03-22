require 'rubygems'
require 'isolate/now'
gem 'minitest'
require 'minitest/autorun'
require 'minitest/mock'

require 'clojurerb'

$:.unshift File.dirname(__FILE__) + '/../lib'
