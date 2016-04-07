Given(/^I am on the login page$/) do
  page(LoginPage).await(timeout: 30)
end