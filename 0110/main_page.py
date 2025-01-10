import streamlit as st

# Set page configuration
st.set_page_config(page_title="PROFOILO 메인 페이지", layout="wide")

# Header
st.title("PROFOILO")
st.markdown("---")

# Navigation Menu (Home, Explore, My Page)
col1, col2, col3 = st.columns([1, 1, 1])
with col1:
    st.button("홈")
with col2:
    st.button("둘러보기")
with col3:
    st.button("마이페이지")

st.markdown("---")

# Banner Section
st.image("https://via.placeholder.com/1200x300", caption="배너 이미지", use_container_width=True)  # Placeholder for banner

# Tag Filters
st.markdown("### 태그 필터")
tags = ["활성 태그", "비활성 태그 1", "비활성 태그 2", "비활성 태그 3"]
cols = st.columns(len(tags))
for i, tag in enumerate(tags):
    with cols[i]:
        st.button(tag)

st.markdown("---")

# Portfolio Grid Section
st.markdown("### 포트폴리오 갤러리")

# Grid of Portfolios
num_portfolios = 8
portfolio_images = ["./chart.png"] * num_portfolios  # Placeholder image URLs
portfolio_names = [f"포트폴리오 {i + 1}" for i in range(num_portfolios)]

cols_per_row = 4  # Number of portfolios per row

for i in range(0, num_portfolios, cols_per_row):
    cols = st.columns(cols_per_row)
    for j, col in enumerate(cols):
        if i + j < num_portfolios:
            with col:
                st.image(portfolio_images[i + j], use_container_width=True)
                st.markdown(f"**{portfolio_names[i + j]}**")
                st.text("기술 스택 요약 및 설명")
                st.text("가격 및 주요 정보")
st.markdown("---")

# Footer Navigation
col1, col2, col3 = st.columns([1, 1, 1])
with col1:
    st.button("메인 화면으로 돌아가기")
with col2:
    st.button("고객센터")
with col3:
    st.button("검색")
